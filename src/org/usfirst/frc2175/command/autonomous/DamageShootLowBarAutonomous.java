package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.single.DriveInchesCommand;
import org.usfirst.frc2175.command.single.ExtendCatapultCommand;
import org.usfirst.frc2175.command.single.RetractCatapultCommand;
import org.usfirst.frc2175.command.single.RunIntakeLiftAtSpeedCommand;
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
        int extraShootLength = autonomousConfig.getExtraShootLength();
        double distanceWithCaution = travelLength + caution;
        double distanceWithShoot = travelLength + extraShootLength;
        double liftIntakeSpeed =
                robotConfig.getIntakeConfig().getLiftIntakeSpeed();

        // TODO Refine numbers if needed
        // TODO Change angle of turn
        // TODO add descriptive comments for each command
        addSequential(new DriveInchesCommand(robotSubsystems, robotControllers,
                distanceWithShoot));
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, 30, true));
        addSequential(new RunIntakeLiftAtSpeedCommand(robotSubsystems,
                -liftIntakeSpeed));
        addSequential(new ExtendCatapultCommand(robotSubsystems));
        addSequential(new RetractCatapultCommand(robotSubsystems));
        addParallel(new RunIntakeLiftAtSpeedCommand(robotSubsystems,
                liftIntakeSpeed));
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, 0, false));
        addSequential(new DriveInchesCommand(robotSubsystems, robotControllers,
                -(distanceWithShoot)));
        addSequential(new DriveInchesCommand(robotSubsystems, robotControllers,
                distanceWithCaution));
    }
}
