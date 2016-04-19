package org.usfirst.frc2175.command.autonomous.twoball;

import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.vision.VisionProcessing;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class StaticMiddleDefenseTwoBallAutonomous extends CommandGroup {
    public StaticMiddleDefenseTwoBallAutonomous(RobotConfig robotConfig,
            RobotSubsystems robotSubsystems, RobotControllers robotControllers,
            VisionProcessing visionProcessing) {

        addParallel(new TwoBallManipulatorCommandGroup(robotConfig,
                robotSubsystems));
        addParallel(new TwoBallPowertrainCommandGroup(robotConfig,
                robotSubsystems, robotControllers, visionProcessing));
    }

}
