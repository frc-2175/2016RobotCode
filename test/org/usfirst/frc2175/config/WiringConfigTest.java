package org.usfirst.frc2175.config;

import org.junit.Ignore;
import org.junit.Test;
import org.usfirst.frc2175.TestBase;
import org.usfirst.frc2175.util.TalonGroup;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import mockit.Mocked;

public class WiringConfigTest extends TestBase {
    @Mocked
    private TalonGroup talongroup;
    @Mocked
    private Encoder encoder;
    @Mocked
    private Gyro gyro;
    @Mocked
    private Solenoid solenoid;
    @Mocked
    private DoubleSolenoid doublesolenoid;
    @Mocked
    private Talon talon;
    @Mocked
    private CANTalon cantalon;
    @Mocked
    private DigitalInput digitalinput;

    @Ignore("Currently broken. Need to mock out more")
    @Test
    public void testWiringConfig_Competition() {
        BaseConfig.setPropertyFileDir(PROPERTY_FILE_DIR_SRC_COMPETITION);
        WiringConfig sut = new WiringConfig();
    }

    @Ignore("Currently broken. Need to mock out more")
    @Test
    public void testWiringConfig_Practice() {
        BaseConfig.setPropertyFileDir(PROPERTY_FILE_DIR_SRC_PRACTICE);
        WiringConfig sut = new WiringConfig();
    }
}
