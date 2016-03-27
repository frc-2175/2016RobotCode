package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.single.ArcadeDriveWithInputsCommand;
import org.usfirst.frc2175.command.single.DriveInchesCommand;
import org.usfirst.frc2175.command.single.RetractCatapultCommand;
import org.usfirst.frc2175.command.single.TurnToHeadingCommand;
import org.usfirst.frc2175.config.AutonomousConfig;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DamageShootLowBarAutonomous extends CommandGroup {

    public DamageShootLowBarAutonomous(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        RobotConfig robotConfig = robotSubsystems.getRobotConfig();
        AutonomousConfig autonomousConfig = robotConfig.getAutonomousConfig();
        double travelLength = autonomousConfig.getTravelLength();
        int caution = autonomousConfig.getCaution();
        double travelLengthWithCaution = travelLength - caution;

        // TODO refine distances
        // weaken lowbar, position robot to shoot, and shoot
        addSequential(new WeakenShootLowBarAutonomous(robotSubsystems,
                robotControllers));
        // retract catapult and make sure robot is aligned
        addParallel(new RetractCatapultCommand(robotSubsystems));
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, -40, true));
        addSequential(new ArcadeDriveWithInputsCommand(robotSubsystems, -.3, 0),
                2);
        // drive back into a place where we can go through the lowbar
        addSequential(
                new DriveInchesCommand(robotSubsystems, robotControllers, 3));
        // turn so the rollerbar is on back and we can go back through lowbar
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, 90, false));
        addSequential(new DriveInchesCommand(robotSubsystems, robotControllers,
                -travelLength));
        addSequential(new DriveInchesCommand(robotSubsystems, robotControllers,
                travelLengthWithCaution));

    }
}
