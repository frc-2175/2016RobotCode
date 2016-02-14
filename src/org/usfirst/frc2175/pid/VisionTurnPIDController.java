package org.usfirst.frc2175.pid;

import org.usfirst.frc2175.config.ControlLoopConfig;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.drivetrain.DrivetrainSubsystem;

public class VisionTurnPIDController extends PIDControllerComplete {
    private DrivetrainSubsystem drivetrainSubsystem;

    public VisionTurnPIDController(RobotSubsystems robotSubsystems,
            RobotConfig robotConfig) {
        ControlLoopConfig controlConfig = robotConfig.getControlLoopConfig();

        double p = controlConfig.getVisionTurnPID_kProportional();
        double i = controlConfig.getVisionTurnPID_kIntegral();
        double d = controlConfig.getVisionTurnPID_kDerivative();
        setPID(p, i, d);

        double min = controlConfig.getVisionTurnPID_minRange();
        double max = controlConfig.getVisionTurnPID_maxRange();
        setOutputRange(min, max);

        double absTolerance = controlConfig.getVisionTurnPID_absTolerance();
        setAbsoluteTolerance(absTolerance);

        this.drivetrainSubsystem = robotSubsystems.getDrivetrainSubsystem();
    }

    @Override
    public double pidGet() {
        return drivetrainSubsystem.getDistanceFromCameraCenter();
    }

    @Override
    public void pidWrite(double output) {
        drivetrainSubsystem.arcadeDrive(0, output);
    }
}