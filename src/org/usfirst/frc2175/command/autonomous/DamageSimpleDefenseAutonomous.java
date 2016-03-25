package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.single.DriveInchesCommand;
import org.usfirst.frc2175.config.AutonomousConfig;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DamageSimpleDefenseAutonomous extends CommandGroup {

    public DamageSimpleDefenseAutonomous(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        RobotConfig robotConfig = robotSubsystems.getRobotConfig();
        AutonomousConfig autonomousConfig = robotConfig.getAutonomousConfig();
        double travelLength = autonomousConfig.getTravelLength();
        int caution = autonomousConfig.getCaution();
        double distanceWithCaution = travelLength - caution;

        // TODO Fix numbers as needed
        // TODO add descriptive comments for each command
        addSequential(new DriveInchesCommand(robotSubsystems, robotControllers,
                travelLength));
        addSequential(new DriveInchesCommand(robotSubsystems, robotControllers,
                -travelLength));
        addSequential(new DriveInchesCommand(robotSubsystems, robotControllers,
                distanceWithCaution));
    }
}
