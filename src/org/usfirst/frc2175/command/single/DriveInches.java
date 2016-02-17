package org.usfirst.frc2175.command.single;

import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveInches extends Command {

    public DriveInches(RobotSubsystems robotSubsystems, double distance) {
        // TODO Fill In
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        // TODO Fill In
    }

    @Override
    protected boolean isFinished() {
        // TODO Fill In
        return false;
    }

    @Override
    protected void end() {
        // TODO Fill in
    }

    @Override
    protected void interrupted() {
        end();
    }
}
