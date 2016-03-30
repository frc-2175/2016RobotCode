package org.usfirst.frc2175.command.single;

import org.usfirst.frc2175.command.BaseCommand;
import org.usfirst.frc2175.pid.GyroTurnPIDController;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.powertrain.PowertrainSubsystem;

public class TurnToFaceGoalWithGyroCommand extends BaseCommand {
    private PowertrainSubsystem powertrainSubsystem;
    private GyroTurnPIDController gyroTurnPIDController;

    private double setpoint;

    public TurnToFaceGoalWithGyroCommand(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        // TODO make sure this doesn't construct once on startup
        this.powertrainSubsystem = robotSubsystems.getPowertrainSubsystem();
        this.gyroTurnPIDController =
                robotControllers.getGyroTurnPIDController();

        // Get the angle from the goal and set the setpoint
        double startingAngleFromGoal = powertrainSubsystem.getGoalAngle();
        this.setpoint = startingAngleFromGoal;
    }

    @Override
    protected void initialize() {
        super.initialize();
        gyroTurnPIDController.setSetpoint(setpoint);
        gyroTurnPIDController.enable();

    }

    @Override
    protected void execute() {

    }

    @Override
    protected boolean isFinished() {
        return gyroTurnPIDController.onTarget();
    }

    @Override
    protected void end() {
        super.end();
        gyroTurnPIDController.disable();
    }

    @Override
    protected void interrupted() {
        end();
    }

}
