package org.usfirst.frc2175.pid;

import java.util.logging.Logger;

import org.usfirst.frc2175.config.ControlLoopConfig;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.powertrain.PowertrainSubsystem;
import org.usfirst.frc2175.subsystem.vision.VisionProcessing;
import org.usfirst.frc2175.util.HighestArrayIndexFinder;

public class VisionTurnPIDController extends PIDControllerComplete {
    private final Logger log = Logger.getLogger(getClass().getName());

    private PowertrainSubsystem powertrainSubsystem;
    private final VisionProcessing visionProcessing;

    public VisionTurnPIDController(RobotSubsystems robotSubsystems,
            RobotConfig robotConfig, VisionProcessing visionProcessing) {
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

        this.visionProcessing = visionProcessing;
    }

    @Override
    public double pidGet() {
        final double value;

        final double goalLocation = visionProcessing.getLargestContourCenterX();

        if (goalLocation == HighestArrayIndexFinder.NO_VALUES) {
            value = getSetpoint();
            disable();
            log.info("Goal not visible; disabled vision turn PID controller");
        } else {
            value = goalLocation;
        }

        return value;
    }

    @Override
    public void pidWrite(double output) {
        powertrainSubsystem.arcadeDrive(0, output);
    }
}
