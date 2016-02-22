package org.usfirst.frc2175.pid;

import org.usfirst.frc2175.config.ControlLoopConfig;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.powertrain.PowertrainSubsystem;

public class VisionTurnPIDController extends PIDControllerComplete {
    private PowertrainSubsystem powertrainSubsystem;

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

        this.powertrainSubsystem = robotSubsystems.getPowertrainSubsystem();
    }

    @Override
    public double getPIDInput() {
        // FIXME Grab a correct value from somewhere
        return 0;
    }

    @Override
    public void writePIDOutput(double output) {
        powertrainSubsystem.arcadeDrive(0, output);
    }
}
