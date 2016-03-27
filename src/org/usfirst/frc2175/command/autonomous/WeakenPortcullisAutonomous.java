package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.single.DriveInchesCommand;
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

        // TODO refine numbers
        // lower boot
        addSequential(new LowerBootCommand(robotSubsystems));
        // drive up to portcullis
        addSequential(new DriveInchesCommand(robotSubsystems, robotControllers,
                platformBeforePortcullis));
        // raise boot to raise portcullis and drive through
        addParallel(new RaiseBootCommand(robotSubsystems));
        addSequential(new DriveInchesCommand(robotSubsystems, robotControllers,
                distanceAfterPortcullis));
    }
}
