package org.usfirst.frc2175.command.group;

import org.usfirst.frc2175.command.single.RunDreamIntakeMainBeltAtSpeedCommand;
import org.usfirst.frc2175.command.single.RunDreamIntakeSideBeltAtSpeedCommand;
import org.usfirst.frc2175.config.IntakeConfig;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RunIntakeOutGroup extends CommandGroup {

    public RunIntakeOutGroup(RobotSubsystems robotSubsystems,
            IntakeConfig intakeConfig) {
        double sideBeltSpeed = intakeConfig.getSideBeltSpeedReverse();
        double mainBeltSpeed = intakeConfig.getMainBeltSpeedReverse();

        addParallel(new RunDreamIntakeSideBeltAtSpeedCommand(robotSubsystems,
                sideBeltSpeed));
        addSequential(new RunDreamIntakeMainBeltAtSpeedCommand(robotSubsystems,
                mainBeltSpeed));

    }
}
