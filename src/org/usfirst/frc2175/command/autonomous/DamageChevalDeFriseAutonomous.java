package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.single.TurnToHeadingCommand;
import org.usfirst.frc2175.config.AutonomousConfig;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DamageChevalDeFriseAutonomous extends CommandGroup {

    public DamageChevalDeFriseAutonomous(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        RobotConfig robotConfig = robotSubsystems.getRobotConfig();
        AutonomousConfig autonomousConfig = robotConfig.getAutonomousConfig();
        int turnAround = autonomousConfig.getTurnAround();

        // weaken cheval de frise
        addSequential(new WeakenChevalDeFriseAutonomous(robotSubsystems,
                robotControllers));
        // turn around
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, turnAround, true));
        // weaken cheval de frise
        addSequential(new WeakenChevalDeFriseAutonomous(robotSubsystems,
                robotControllers));
        // turn around
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, turnAround, true));
        // weaken cheval de frise
        addSequential(new WeakenChevalDeFriseAutonomous(robotSubsystems,
                robotControllers));
    }
}
