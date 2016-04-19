package org.usfirst.frc2175.command.autonomous.twoball;

import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TwoBallManipulatorCommandGroup extends CommandGroup {
    private RobotConfig robotConfig;
    private RobotSubsystems robotSubsystems;

    public TwoBallManipulatorCommandGroup(RobotConfig robotConfig,
            RobotSubsystems robotSubsystems) {
        this.robotConfig = robotConfig;
        this.robotSubsystems = robotSubsystems;

        /*-------------BEGIN ROUTINE HERE---------------------*/

    }

}
