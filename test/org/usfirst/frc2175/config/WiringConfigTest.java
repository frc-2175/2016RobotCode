package org.usfirst.frc2175.config;

import org.junit.Test;
import org.usfirst.frc2175.TestBase;
import org.usfirst.frc2175.util.TalonGroup;

import edu.wpi.first.wpilibj.AnalogGyro;
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
    @Mocked
    private AnalogGyro analogGyro;

    @Test
    public void testWiringConfig_Competition()
            throws IllegalArgumentException, IllegalAccessException {
        BaseConfig.setPropertyFileDir(PROPERTY_FILE_DIR_SRC_COMPETITION);
        WiringConfig sut = new WiringConfig();
        assertInstanceVariablesNotNull(sut);
        assertArraysNotZeroLength(sut);
    }

    @Test
    public void testWiringConfig_Practice()
            throws IllegalArgumentException, IllegalAccessException {
        BaseConfig.setPropertyFileDir(PROPERTY_FILE_DIR_SRC_PRACTICE);
        WiringConfig sut = new WiringConfig();
        assertInstanceVariablesNotNull(sut);
        assertArraysNotZeroLength(sut);
    }

    @Test
    public void testWiringConfig_UniquePropertiesSequence_Motor_Competition() {
        String propertyRegex = ".*[.]talon[.].*";
        String propertyFileDirectory = PROPERTY_FILE_DIR_SRC_COMPETITION;

        commonTestUniqueProperties(propertyFileDirectory, propertyRegex);
    }

    @Test
    public void testWiringConfig_UniquePropertiesSequence_Motor_Practice() {
        String propertyRegex = ".*[.]talon[.].*";
        String propertyFileDirectory = PROPERTY_FILE_DIR_SRC_PRACTICE;

        commonTestUniqueProperties(propertyFileDirectory, propertyRegex);
    }

    @Test
    public void testWiringConfig_UniquePropertiesSequence_Digital_Competition() {
        String propertyRegex = ".*[.]digital[.].*port";
        String propertyFileDirectory = PROPERTY_FILE_DIR_SRC_COMPETITION;

        commonTestUniqueProperties(propertyFileDirectory, propertyRegex);
    }

    @Test
    public void testWiringConfig_UniquePropertiesSequence_Digital_Practice() {
        String propertyRegex = ".*[.]digital[.].*port";
        String propertyFileDirectory = PROPERTY_FILE_DIR_SRC_PRACTICE;

        commonTestUniqueProperties(propertyFileDirectory, propertyRegex);
    }

    @Test
    public void testWiringConfig_UniquePropertiesSequence_Analog_Competition() {
        String propertyRegex = ".*[.]analog[.].*";
        String propertyFileDirectory = PROPERTY_FILE_DIR_SRC_COMPETITION;

        commonTestUniqueProperties(propertyFileDirectory, propertyRegex);
    }

    @Test
    public void testWiringConfig_UniquePropertiesSequence_Analog_Practice() {
        String propertyRegex = ".*[.]analog[.].*";
        String propertyFileDirectory = PROPERTY_FILE_DIR_SRC_PRACTICE;

        commonTestUniqueProperties(propertyFileDirectory, propertyRegex);
    }

    @Test
    public void testWiringConfig_UniquePropertiesSequence_Solenoid_Competition() {
        String propertyRegex = ".*[.]solenoid[.].*";
        String propertyFileDirectory = PROPERTY_FILE_DIR_SRC_COMPETITION;

        commonTestUniqueProperties(propertyFileDirectory, propertyRegex);
    }

    @Test
    public void testWiringConfig_UniquePropertiesSequence_Solenoid_Practice() {
        String propertyRegex = ".*[.]solenoid[.].*";
        String propertyFileDirectory = PROPERTY_FILE_DIR_SRC_PRACTICE;

        commonTestUniqueProperties(propertyFileDirectory, propertyRegex);
    }

    private void commonTestUniqueProperties(String propertyFileDirectory,
            String propertyRegex) {
        BaseConfig.setPropertyFileDir(propertyFileDirectory);
        BaseConfig baseConfig = new WiringConfig();

        assertNoDuplicatePropertyValues(propertyRegex, baseConfig);
    }
}
