package org.usfirst.frc2175.command.group;

import org.usfirst.frc2175.command.single.ExtendLowGoalSolenoidCommand;
import org.usfirst.frc2175.config.IntakeConfig;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RunIntakeInAndExtendLowGoalSolenoidGroup extends CommandGroup {
    public RunIntakeInAndExtendLowGoalSolenoidGroup(
            RobotSubsystems robotSubsystems, IntakeConfig intakeConfig) {
        addParallel(new RunIntakeInGroup(robotSubsystems, intakeConfig));
        addSequential(new ExtendLowGoalSolenoidCommand(robotSubsystems));
    }
}
