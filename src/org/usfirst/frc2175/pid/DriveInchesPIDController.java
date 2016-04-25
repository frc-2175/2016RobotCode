package org.usfirst.frc2175.pid;

import org.usfirst.frc2175.config.ControlLoopConfig;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.powertrain.PowertrainSubsystem;

import edu.wpi.first.wpilibj.PIDSourceType;

public class DriveInchesPIDController extends PIDControllerComplete {
    private PowertrainSubsystem powertrainSubsystem;

    public DriveInchesPIDController(RobotSubsystems robotSubsystems,
            RobotConfig robotConfig) {
        ControlLoopConfig controlConfig = robotConfig.getControlLoopConfig();

        double p = controlConfig.getDriveInchesPID_kProportional();
        double i = controlConfig.getDriveInchesPID_kIntegral();
        double d = controlConfig.getDriveInchesPID_kDerivative();
        setPID(p, i, d);

        double min = controlConfig.getDriveInchesPID_minRange();
        double max = controlConfig.getDriveInchesPID_maxRange();
        setOutputRange(min, max);

        double absTolerance = controlConfig.getDriveInchesPID_absTolerance();
        setAbsoluteTolerance(absTolerance);
        setPIDSourceType(PIDSourceType.kDisplacement);

        this.powertrainSubsystem = robotSubsystems.getPowertrainSubsystem();
    }

    @Override
    public double pidGet() {
        return powertrainSubsystem.getMeanEncoderDistance();
    }

    @Override
    public void pidWrite(double output) {
        double sign = output / Math.abs(output);
        powertrainSubsystem.arcadeDrive(output, 0);
    }

}
