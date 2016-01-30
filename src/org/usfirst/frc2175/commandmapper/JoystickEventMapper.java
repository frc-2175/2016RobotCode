package org.usfirst.frc2175.commandmapper;

import org.usfirst.frc2175.command.single.ExtendCatapult;
import org.usfirst.frc2175.command.single.RetractCatapult;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.driverstation.DriverStation;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Map {@link Command}s to joystick buttons.
 */
public class JoystickEventMapper {

    public JoystickEventMapper(RobotConfig robotConfig, DriverStation driverStation, RobotSubsystems robotSubsystems) {

        JoystickButton extendCatapult = robotConfig.getGamepadConfig().getExtendCatapultButton();
        extendCatapult.whenPressed(new ExtendCatapult(driverStation, robotSubsystems));

        JoystickButton retractCatapult = robotConfig.getGamepadConfig().getRetractCatapultButton();
        retractCatapult.whenPressed(new RetractCatapult(driverStation, robotSubsystems));

    }
}
