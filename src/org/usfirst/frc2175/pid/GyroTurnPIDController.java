package org.usfirst.frc2175.pid;

import org.usfirst.frc2175.config.ControlLoopConfig;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.powertrain.PowertrainSubsystem;

public class GyroTurnPIDController extends PIDControllerComplete {
    private PowertrainSubsystem powertrainSubsystem;

    public GyroTurnPIDController(RobotSubsystems robotSubsystems,
            RobotConfig robotConfig) {
        ControlLoopConfig controlConfig = robotConfig.getControlLoopConfig();

        double p = controlConfig.getGyroTurnPID_kProportional();
        double i = controlConfig.getGyroTurnPID_kIntegral();
        double d = controlConfig.getGyroTurnPID_kDerivative();
        setPID(p, i, d);

        double min = controlConfig.getGyroTurnPID_minRange();
        double max = controlConfig.getGyroTurnPID_maxRange();
        setOutputRange(min, max);

        double absTolerance = controlConfig.getGyroTurnPID_absTolerance();
        setAbsoluteTolerance(absTolerance);

        this.powertrainSubsystem = robotSubsystems.getPowertrainSubsystem();
    }

    @Override
    public double pidGet() {
        return powertrainSubsystem.getGyroAngle();
    }

    @Override
    public void pidWrite(double output) {
        powertrainSubsystem.arcadeDrive(0, output);
    }

}