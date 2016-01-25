# 2016RobotCode
The Fighting Calculators' code for the FRC 2016 game, *FIRST* Stronghold!

## Overview
This project is built on *FIRST*'s command-based programming framework, with structural changes to allow for dependency injection and better separation of concerns. For an overview of command-based programming, see [*FIRST*'s documentation](https://wpilib.screenstepslive.com/s/4485/m/13809/l/241892-what-is-command-based-programming).

Below is an overview of our different parts and how they all fit together.

### RobotConfig
The `Config` classes in our project contain all robot configuration info. This is any static configuration that we load when the robot starts.

[`RobotConfig`](https://github.com/frc-2175/2016RobotCode/blob/master/src/org/usfirst/frc2175/config/RobotConfig.java) contains references to all of our individual `Config` classes, like [`JoysticksConfig`](https://github.com/frc-2175/2016RobotCode/blob/master/src/org/usfirst/frc2175/config/JoysticksConfig.java) and [`WiringConfig`](https://github.com/frc-2175/2016RobotCode/blob/master/src/org/usfirst/frc2175/config/WiringConfig.java). Each of these config classes loads values from a `.properties` file on the roboRIO and exposes those properties using getter methods.

### DriverStation (formerly OI)
[`DriverStation`](https://github.com/frc-2175/2016RobotCode/blob/master/src/org/usfirst/frc2175/driverstation/DriverStation.java) contains the actual joystick instances. It requires an instance of `RobotConfig` to be injected so that it can look up port numbers from `JoysticksConfig` and `GamepadConfig`.

`DriverStation` provides methods that enable Commands to access values from the joysticks. `DriverStation` also handles any input signal manipulation, such as deadband control.

**Note:** Unlike in previous years, `DriverStation` will **not** handle output signal manipulation such as precision or turbo mode. That is the responsibility of the drive command! However, `DriverStation` may provide a method such as `isPrecisionModeEnabled()` to assist the command.

**Also note:** Unlike in previous years, `DriverStation` will **not** be mapping commands to joystick buttons! That is the responsibility of `JoystickEventMapper`, outlined below.

### RobotSubsystems
The [`RobotSubsystems`](https://github.com/frc-2175/2016RobotCode/blob/master/src/org/usfirst/frc2175/subsystem/RobotSubsystems.java) class contains references to all of our individual `Subsystem` classes. It requires an instance of `RobotConfig` to be injected so that each subsystem can look up any values it needs. It also requires an instance of `DriverStation` for the purpose of setting up default commands on each subsystem.

`RobotSubsystems` conveniently groups our subsystem references together so they can be passed to each of our commands.

### JoystickEventMapper
The [`JoystickEventMapper`](https://github.com/frc-2175/2016RobotCode/blob/master/src/org/usfirst/frc2175/commandmapper/JoystickEventMapper.java) class maps commands to joystick buttons. This is basically the same behavior as any other year, but this year we are not doing this step from within `DriverStation` (formerly `OI`).
