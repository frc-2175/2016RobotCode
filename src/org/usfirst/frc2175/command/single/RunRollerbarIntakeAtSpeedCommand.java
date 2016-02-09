package org.usfirst.frc2175.command.single;

import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.intake.RollerbarIntakeSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RunRollerbarIntakeAtSpeedCommand extends Command {

    private double speed;
    private RollerbarIntakeSubsystem rollerbarIntakeSubsystem;

    public RunRollerbarIntakeAtSpeedCommand(RobotSubsystems robotSubsystems,
            double speed) {
        rollerbarIntakeSubsystem =
                robotSubsystems.getRollerbarIntakeSubsystem();
        this.speed = speed;

        requires(rollerbarIntakeSubsystem);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        rollerbarIntakeSubsystem.setRollerbarSpeed(speed);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        rollerbarIntakeSubsystem.setRollerbarSpeed(0);
    }

    @Override
    protected void interrupted() {
        end();
    }
}
