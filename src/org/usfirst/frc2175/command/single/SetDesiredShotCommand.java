package org.usfirst.frc2175.command.single;

import java.util.logging.Logger;

import org.usfirst.frc2175.command.BaseCommand;
import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.shooter.CatapultShooterSubsystem;
import org.usfirst.frc2175.subsystem.shooter.ShotType;

/**
 *
 */
public class SetDesiredShotCommand extends BaseCommand {
    private CatapultShooterSubsystem catapultSubsystem;
    private final Logger log = Logger.getLogger(getClass().getName());

    private ShotType shotType;

    public SetDesiredShotCommand(RobotSubsystems robotSubsystems,
            ShotType shotType) {
        catapultSubsystem = robotSubsystems.getCatapultShooterSubsystem();
        this.shotType = shotType;

        requires(robotSubsystems.getCatapultShooterSubsystem());
    }

    @Override
    protected void initialize() {
        super.initialize();
        catapultSubsystem.setShotType(this.shotType);
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
