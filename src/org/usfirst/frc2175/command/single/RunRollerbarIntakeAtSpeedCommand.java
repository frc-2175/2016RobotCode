package org.usfirst.frc2175.command.single;

import org.usfirst.frc2175.command.BaseCommand;
import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.intake.RollerbarIntakeSubsystem;

/**
 *
 */
public class RunRollerbarIntakeAtSpeedCommand extends BaseCommand {

    private double speed;
    private RollerbarIntakeSubsystem rollerbarIntakeSubsystem;

    public RunRollerbarIntakeAtSpeedCommand(RobotSubsystems robotSubsystems,
            double speed) {
        this.rollerbarIntakeSubsystem =
                robotSubsystems.getRollerbarIntakeSubsystem();
        this.speed = speed;

        requires(this.rollerbarIntakeSubsystem);
    }

    @Override
    protected void initialize() {
        super.initialize();
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
        super.end();
        rollerbarIntakeSubsystem.setRollerbarSpeed(0);
    }

    @Override
    protected void interrupted() {
        end();
    }
}
