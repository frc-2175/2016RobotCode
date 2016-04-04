package org.usfirst.frc2175.command.group;

import org.usfirst.frc2175.command.single.RetractLowGoalSolenoidCommand;
import org.usfirst.frc2175.config.IntakeConfig;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RunIntakeOutAndRetractLowGoalSolenoidGroup extends CommandGroup {
    public RunIntakeOutAndRetractLowGoalSolenoidGroup(
            RobotSubsystems robotSubsystems, IntakeConfig intakeConfig) {
        addParallel(new RunIntakeOutGroup(robotSubsystems, intakeConfig));
        addSequential(new RetractLowGoalSolenoidCommand(robotSubsystems));
    }
}
