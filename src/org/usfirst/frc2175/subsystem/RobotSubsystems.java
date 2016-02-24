package org.usfirst.frc2175.subsystem;

import java.util.logging.Logger;

import org.usfirst.frc2175.command.single.ArcadeDriveWithJoysticksCommand;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.driverstation.DriverStation;
import org.usfirst.frc2175.subsystem.climber.ClimberSubsystem;
import org.usfirst.frc2175.subsystem.intake.RollerbarIntakeSubsystem;
import org.usfirst.frc2175.subsystem.manipulator.ManipulatorSubsystem;
import org.usfirst.frc2175.subsystem.powertrain.PowertrainSubsystem;
import org.usfirst.frc2175.subsystem.shooter.CatapultShooterSubsystem;

public class RobotSubsystems {
    private final Logger log = Logger.getLogger(getClass().getName());

    private final RobotConfig robotConfig;
    private final DriverStation driverStation;

    // TODO add remaining subsystems
    private final RollerbarIntakeSubsystem rollerbarIntakeSubsystem;
    private final PowertrainSubsystem powertrainSubsystem;
    private final CatapultShooterSubsystem catapultShooterSubsystem;
    private final ManipulatorSubsystem manipulatorSubsystem;
    private final ClimberSubsystem climberSubsystem;

    public RobotSubsystems(RobotConfig robotConfig,
            DriverStation driverStation) {
        log.info("Configuring class=" + getClass());

        this.robotConfig = robotConfig;
        this.driverStation = driverStation;

        rollerbarIntakeSubsystem = new RollerbarIntakeSubsystem(robotConfig);
        powertrainSubsystem = new PowertrainSubsystem(robotConfig);
        catapultShooterSubsystem = new CatapultShooterSubsystem(robotConfig);
        manipulatorSubsystem = new ManipulatorSubsystem(robotConfig);
        climberSubsystem = new ClimberSubsystem(robotConfig);

        setDefaultCommands();
    }

    public ManipulatorSubsystem getManipulatorSubsystem() {
        return manipulatorSubsystem;
    }

    protected void setDefaultCommands() {
        powertrainSubsystem.setDefaultCommand(
                new ArcadeDriveWithJoysticksCommand(driverStation, this));
    }

    public RollerbarIntakeSubsystem getRollerbarIntakeSubsystem() {
        return rollerbarIntakeSubsystem;
    }

    public PowertrainSubsystem getPowertrainSubsystem() {
        return powertrainSubsystem;
    }

    public CatapultShooterSubsystem getCatapultShooterSubsystem() {
        return catapultShooterSubsystem;
    }

    public ClimberSubsystem getClimberSubsystem() {
        return climberSubsystem;
    }

    public RobotConfig getRobotConfig() {
        return robotConfig;
    }
}
