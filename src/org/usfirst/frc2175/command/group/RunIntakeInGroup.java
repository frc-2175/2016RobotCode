package org.usfirst.frc2175.command.group;

import org.usfirst.frc2175.command.single.RunDreamIntakeMainBeltAtSpeedCommand;
import org.usfirst.frc2175.command.single.RunDreamIntakeSideBeltAtSpeedCommand;
import org.usfirst.frc2175.config.IntakeConfig;
import org.usfirst.frc2175.driverstation.DriverStation;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RunIntakeInGroup extends CommandGroup {

    public RunIntakeInGroup(DriverStation driverStation,
            RobotSubsystems robotSubsystems, IntakeConfig intakeConfig) {
        double sideBeltSpeed = intakeConfig.getSideBeltSpeedForward();
        double mainBeltSpeed = intakeConfig.getMainBeltSpeedForward();

        addParallel(new RunDreamIntakeSideBeltAtSpeedCommand(driverStation,
                robotSubsystems, sideBeltSpeed));
        addSequential(new RunDreamIntakeMainBeltAtSpeedCommand(driverStation,
                robotSubsystems, mainBeltSpeed));

    }
}
