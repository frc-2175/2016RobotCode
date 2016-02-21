package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.single.DriveInches;
import org.usfirst.frc2175.command.single.LowerBootCommand;
import org.usfirst.frc2175.command.single.RaiseBootCommand;
import org.usfirst.frc2175.config.AutonomousConfig;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class WeakenPortcullisAutonomous extends CommandGroup {

    public WeakenPortcullisAutonomous(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        RobotConfig robotConfig = robotSubsystems.getRobotConfig();
        AutonomousConfig autonomousConfig = robotConfig.getAutonomousConfig();
        double travelLength = autonomousConfig.getTravelLength();
        int platformBeforePortcullis =
                autonomousConfig.getPlatformBeforePortcullis();
        double distanceAfterPortcullis =
                travelLength - platformBeforePortcullis;

        // TODO Refine numbers if needed
        addSequential(new DriveInches(robotSubsystems, robotControllers,
                platformBeforePortcullis));
        addSequential(new RaiseBootCommand(robotSubsystems));
        addParallel(new DriveInches(robotSubsystems, robotControllers,
                distanceAfterPortcullis));
        addSequential(new LowerBootCommand(robotSubsystems));
    }
}
