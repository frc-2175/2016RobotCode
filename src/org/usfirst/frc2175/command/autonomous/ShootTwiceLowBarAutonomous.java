package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.group.RunIntakeInGroup;
import org.usfirst.frc2175.command.single.ArcadeDriveWithInputsCommand;
import org.usfirst.frc2175.command.single.DriveInchesCommand;
import org.usfirst.frc2175.command.single.RetractCatapultCommand;
import org.usfirst.frc2175.command.single.TurnToHeadingCommand;
import org.usfirst.frc2175.config.AutonomousConfig;
import org.usfirst.frc2175.config.IntakeConfig;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShootTwiceLowBarAutonomous extends CommandGroup {

    public ShootTwiceLowBarAutonomous(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        RobotConfig robotConfig = robotSubsystems.getRobotConfig();
        AutonomousConfig autonomousConfig = robotConfig.getAutonomousConfig();
        IntakeConfig intakeConfig = robotConfig.getIntakeConfig();
        double travelLength = autonomousConfig.getTravelLength();

        // TODO refine distances
        // weaken lowbar, position ourselves to shoot, and shoot
        addSequential(new WeakenShootLowBarAutonomous(robotSubsystems,
                robotControllers));
        // retract catapult and make sure we are aligned
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
        // drive through lowbar
        addSequential(new DriveInchesCommand(robotSubsystems, robotControllers,
                -3 * travelLength / 4));
        // turn on rollerbar spinning whilst going through lowbar
        addParallel(new DriveInchesCommand(robotSubsystems, robotControllers,
                -travelLength / 4));
        addSequential(new RunIntakeInGroup(robotSubsystems, intakeConfig), 4);
        addSequential(new WeakenShootLowBarAutonomous(robotSubsystems,
                robotControllers));
    }
}
