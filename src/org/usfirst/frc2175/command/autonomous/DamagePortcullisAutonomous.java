package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.single.TurnToHeadingCommand;
import org.usfirst.frc2175.config.AutonomousConfig;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DamagePortcullisAutonomous extends CommandGroup {

    public DamagePortcullisAutonomous(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        RobotConfig robotConfig = robotSubsystems.getRobotConfig();
        AutonomousConfig autonomousConfig = robotConfig.getAutonomousConfig();
        int turnAround = autonomousConfig.getTurnAround();

        // drive through portcullis
        addSequential(new WeakenPortcullisAutonomous(robotSubsystems,
                robotControllers));
        // turn around
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, turnAround, false));
        // drive through portcullis
        addSequential(new WeakenPortcullisAutonomous(robotSubsystems,
                robotControllers));
        // turn around
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, turnAround, false));
        // drive through portcullis
        addSequential(new WeakenPortcullisAutonomous(robotSubsystems,
                robotControllers));
    }

}
