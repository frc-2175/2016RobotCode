package org.usfirst.frc2175.command.single;

import org.usfirst.frc2175.command.BaseCommand;
import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.shooter.CatapultShooterSubsystem;

/**
 *
 */
public class CycleDesiredShotCommand extends BaseCommand {
    private CatapultShooterSubsystem catapultSubsystem;

    public CycleDesiredShotCommand(RobotSubsystems robotSubsystems) {
        catapultSubsystem = robotSubsystems.getCatapultShooterSubsystem();

        requires(robotSubsystems.getCatapultShooterSubsystem());
    }

    @Override
    protected void initialize() {
        super.initialize();
        catapultSubsystem.cycleShotType();
    }

    @Override
    protected void execute() {
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

    @Override
    protected void end() {
        super.end();
    }

    @Override
    protected void interrupted() {
        end();
    }
}
