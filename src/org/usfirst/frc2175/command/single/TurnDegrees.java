package org.usfirst.frc2175.command.single;

import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnDegrees extends Command {

    public TurnDegrees(RobotSubsystems robotSubsystems, double degrees) {
        // TODO Fill in
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        // TODO Fill in
    }

    @Override
    protected boolean isFinished() {
        // TODO Fill in
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
