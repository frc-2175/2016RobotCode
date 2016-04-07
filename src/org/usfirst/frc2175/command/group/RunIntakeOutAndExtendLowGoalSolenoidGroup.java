package org.usfirst.frc2175.command.group;

import org.usfirst.frc2175.command.single.ExtendLowGoalSolenoidCommand;
import org.usfirst.frc2175.config.IntakeConfig;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RunIntakeOutAndExtendLowGoalSolenoidGroup extends CommandGroup {
    public RunIntakeOutAndExtendLowGoalSolenoidGroup(
            RobotSubsystems robotSubsystems, IntakeConfig intakeConfig) {
        addParallel(new RunIntakeOutGroup(robotSubsystems, intakeConfig));
        addSequential(new ExtendLowGoalSolenoidCommand(robotSubsystems));
    }
}
