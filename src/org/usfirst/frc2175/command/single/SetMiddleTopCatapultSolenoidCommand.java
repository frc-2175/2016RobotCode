package org.usfirst.frc2175.command.single;

import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.shooter.CatapultShooterSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetMiddleTopCatapultSolenoidCommand extends Command {
    private final CatapultShooterSubsystem catapultShooterSubsystem;
    private boolean isOn;

    public SetMiddleTopCatapultSolenoidCommand(RobotSubsystems robotSubsystems,
            boolean isOn) {
        this.catapultShooterSubsystem =
                robotSubsystems.getCatapultShooterSubsystem();
        this.isOn = isOn;

        requires(catapultShooterSubsystem);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        catapultShooterSubsystem.setMiddleTopSolenoid(isOn);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
