package org.usfirst.frc2175.command.autonomous;

import org.usfirst.frc2175.command.single.DriveInches;
import org.usfirst.frc2175.command.single.ExtendCatapultCommand;
import org.usfirst.frc2175.command.single.LowerBootCommand;
import org.usfirst.frc2175.command.single.RaiseBootCommand;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class WeakenShootPortcullisAutonomous extends CommandGroup {
    private double travelLength;
    private int platformBeforePortcullis;

    public WeakenShootPortcullisAutonomous(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        travelLength = robotSubsystems.getRobotConfig().getAutonomousConfig()
                .getTravelLength();
        platformBeforePortcullis = robotSubsystems.getRobotConfig()
                .getAutonomousConfig().getExtraShootLength();
        // TODO Refine numbers if needed
        addSequential(new DriveInches(robotSubsystems, robotControllers,
                platformBeforePortcullis));
        addSequential(new RaiseBootCommand(robotSubsystems));
        addParallel(new DriveInches(robotSubsystems, robotControllers,
                travelLength - platformBeforePortcullis));
        addSequential(new LowerBootCommand(robotSubsystems));
        addSequential(new ExtendCatapultCommand(robotSubsystems));
    }

}
