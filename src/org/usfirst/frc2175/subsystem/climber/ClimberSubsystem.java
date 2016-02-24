package org.usfirst.frc2175.subsystem.climber;

import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.config.WiringConfig;
import org.usfirst.frc2175.subsystem.BaseSubsystem;
import org.usfirst.frc2175.util.TalonGroup;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;

public class ClimberSubsystem extends BaseSubsystem {
    private Solenoid climberSolenoid;
    private TalonGroup climberTalonGroup;
    private DigitalInput climberExtendedSwitch;
    private DigitalInput climberUpSwitch;

    public ClimberSubsystem(RobotConfig robotConfig) {
        WiringConfig wiringConfig = robotConfig.getWiringConfig();
        climberSolenoid = wiringConfig.getClimberSolenoid();
        climberTalonGroup = wiringConfig.getClimberTalonHandler();
        climberExtendedSwitch = wiringConfig.getClimberExtendedSwitch();
        climberUpSwitch = wiringConfig.getClimberUpSwitch();
    }

    public void setClimberPosition(boolean isUp) {
        if (isUp) {
            climberSolenoid.set(true);
        } else {
            climberSolenoid.set(false);
        }
    }

    protected double determineSafetyCheckedClimberExtendSpeed(double speed) {
        double setSpeed;
        if (!isClimberUp()) {
            setSpeed = 0;
        } else if (isClimberExtended() && speed > 0) {
            setSpeed = 0;
        } else {
            setSpeed = speed;
        }
        return setSpeed;

    }

    public void setClimberSpeed(double speed) {
        climberTalonGroup.set(determineSafetyCheckedClimberExtendSpeed(speed));

    }

    public boolean isClimberExtended() {
        return climberExtendedSwitch.get();
    }

    public boolean isClimberUp() {
        return climberUpSwitch.get();
    }

}
