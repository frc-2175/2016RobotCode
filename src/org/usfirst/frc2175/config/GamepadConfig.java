package org.usfirst.frc2175.config;

import java.util.Properties;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * Gamepad configuration values - "weapons" controls.
 *
 * Set the values in the gamepad properties file.
 *
 * Do not use values directly in code, use these configuration methods.
 */
public class GamepadConfig extends BaseConfig {
    private static final String PROPERTY_FILE_NAME = "gamepad.properties";

    private Joystick weaponGamepad;
    private Joystick driverGamepad;

    // weaponGamepad buttons
    private JoystickButton extendCatapult;
    private JoystickButton extendPuncher;
    private JoystickButton cycleShot;
    private JoystickButton runIntakeInAndRetractLowGoalSolenoid;
    private JoystickButton runIntakeOutAndExtendLowGoalSolenoid;
    private JoystickButton faceGoalAndShoot;
    private JoystickButton lowerBoot;
    private JoystickButton raiseBoot;
    private JoystickButton toggleLight;
    private JoystickButton lowerIntake;
    private JoystickButton raiseIntake;

    // driverGamepad buttons

    private JoystickButton shiftHigh;

    @Override
    public String getPropertyFileName() {
        return PROPERTY_FILE_NAME;
    }

    @Override
    protected void configure(Properties properties) {
        configureWeaponGamepad(properties);
        configureDriverGamepad(properties);
    }

    protected void configureWeaponGamepad(Properties properties) {
        weaponGamepad = new Joystick(
                getIntPropertyValue("weaponGamepad.port", properties));

        extendCatapult = new JoystickButton(weaponGamepad, getIntPropertyValue(
                "weapon.button.catapult.extend", properties));
        extendPuncher = new JoystickButton(weaponGamepad, getIntPropertyValue(
                "weapon.button.puncher.extend", properties));
        runIntakeInAndRetractLowGoalSolenoid = new JoystickButton(weaponGamepad,
                getIntPropertyValue("weapon.button.intake.in", properties));
        runIntakeOutAndExtendLowGoalSolenoid = new JoystickButton(weaponGamepad,
                getIntPropertyValue("weapon.button.intake.out", properties));
        faceGoalAndShoot = new JoystickButton(weaponGamepad,
                getIntPropertyValue("weapon.button.action.test", properties));
        lowerBoot = new JoystickButton(weaponGamepad,
                getIntPropertyValue("weapon.button.boot.lower", properties));
        raiseBoot = new JoystickButton(weaponGamepad,
                getIntPropertyValue("weapon.button.boot.raise", properties));
        raiseIntake = new JoystickButton(weaponGamepad,
                getIntPropertyValue("weapon.button.intake.raise", properties));
        lowerIntake = new JoystickButton(weaponGamepad,
                getIntPropertyValue("weapon.button.intake.lower", properties));
        toggleLight = new JoystickButton(weaponGamepad,
                getIntPropertyValue("weapon.button.light.toggle", properties));
        cycleShot = new JoystickButton(weaponGamepad, getIntPropertyValue(
                "weapon.button.catapult.cycleShot", properties));
    }

    protected void configureDriverGamepad(Properties properties) {
        driverGamepad = new Joystick(
                getIntPropertyValue("driverGamepad.port", properties));
        shiftHigh = new JoystickButton(driverGamepad,
                getIntPropertyValue("driver.button.shift.high", properties));
    }

    public JoystickButton getShiftHigh() {
        return shiftHigh;
    }

    public JoystickButton getToggleLight() {
        return toggleLight;
    }

    public JoystickButton getCycleShot() {
        return cycleShot;
    }

    public void setShortShot(JoystickButton shortShot) {
        this.cycleShot = shortShot;
    }

    public JoystickButton getLowerIntake() {
        return lowerIntake;
    }

    public JoystickButton getRaiseIntake() {
        return raiseIntake;
    }

    public JoystickButton getExtendCatapult() {
        return extendCatapult;
    }

    public JoystickButton getExtendPuncher() {
        return extendPuncher;
    }

    public JoystickButton getRunIntakeIn() {
        return runIntakeInAndRetractLowGoalSolenoid;
    }

    public JoystickButton getRunIntakeOut() {
        return runIntakeOutAndExtendLowGoalSolenoid;
    }

    public JoystickButton getFaceGoalAndShoot() {
        return faceGoalAndShoot;
    }

    public JoystickButton getLowerBoot() {
        return lowerBoot;
    }

    public JoystickButton getRaiseBoot() {
        return raiseBoot;
    }

    public Joystick getWeaponGamepad() {
        return weaponGamepad;
    }

    public Joystick getDriverGamepad() {
        return driverGamepad;
    }

}
