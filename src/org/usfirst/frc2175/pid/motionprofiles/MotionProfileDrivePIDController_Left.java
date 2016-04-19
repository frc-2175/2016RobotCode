package org.usfirst.frc2175.pid.motionprofiles;

import org.usfirst.frc2175.config.ControlLoopConfig;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.pid.PIDControllerComplete;
import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.powertrain.PowertrainSubsystem;

import edu.wpi.first.wpilibj.PIDSourceType;

public class MotionProfileDrivePIDController_Left
        extends PIDControllerComplete {
    private PowertrainSubsystem powertrainSubsystem;

    public MotionProfileDrivePIDController_Left(RobotSubsystems robotSubsystems,
            RobotConfig robotConfig) {
        this.powertrainSubsystem = robotSubsystems.getPowertrainSubsystem();
        ControlLoopConfig controlLoopConfig =
                robotConfig.getControlLoopConfig();

        // TODO add all of the stuff to set PID constants

        setPIDSourceType(PIDSourceType.kRate);

    }

    @Override
    public double pidGet() {
        return powertrainSubsystem.getLeftEncoderSpeed();
    }

    @Override
    public void pidWrite(double output) {
        powertrainSubsystem.getLeftDriveSideTalonGroup().set(output);
    }

}
