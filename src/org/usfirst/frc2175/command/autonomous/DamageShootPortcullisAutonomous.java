package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.single.RetractCatapultCommand;
import org.usfirst.frc2175.command.single.TurnToHeadingCommand;
import org.usfirst.frc2175.config.AutonomousConfig;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DamageShootPortcullisAutonomous extends CommandGroup {

    public DamageShootPortcullisAutonomous(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        RobotConfig robotConfig = robotSubsystems.getRobotConfig();
        AutonomousConfig autonomousConfig = robotConfig.getAutonomousConfig();
        int turnAround = autonomousConfig.getTurnAround();

        // TODO refine numbers
        // drive through portcullis
        addSequential(new WeakenShootPortcullisAutonomous(robotSubsystems,
                robotControllers));
        // retract catapult and turn around
        addParallel(new RetractCatapultCommand(robotSubsystems));
        // turn around
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, turnAround, true));
        // drive through portcullis
        addSequential(new WeakenPortcullisAutonomous(robotSubsystems,
                robotControllers));
        // turn around
        addSequential(new TurnToHeadingCommand(robotSubsystems,
                robotControllers, turnAround, true));
        // drive through portcullis
        addSequential(new WeakenPortcullisAutonomous(robotSubsystems,
                robotControllers));
    }
}
