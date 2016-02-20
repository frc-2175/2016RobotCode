package org.usfirst.frc2175;

import java.util.logging.Logger;

import org.usfirst.frc2175.commandmapper.JoystickEventMapper;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.driverstation.DeadbandCalculator;
import org.usfirst.frc2175.driverstation.DriverStation;
import org.usfirst.frc2175.driverstation.SmartDashboardHandler;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    private final RobotConfig robotConfig = new RobotConfig();
    private final DriverStation driverStation =
            new DriverStation(robotConfig, new DeadbandCalculator());
    private final RobotSubsystems robotSubsystems =
            new RobotSubsystems(robotConfig, driverStation);
    private final RobotControllers robotControllers =
            new RobotControllers(robotConfig, robotSubsystems);
    private final JoystickEventMapper joystickEventMapper =
            new JoystickEventMapper(robotConfig, driverStation, robotSubsystems,
                    robotControllers);
    private final SmartDashboardHandler smartDashboardHandler =
            new SmartDashboardHandler(robotSubsystems, robotControllers);

    // This must come after RobotConfig
    private final Logger log = Logger.getLogger(getClass().getName());

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
    }

    /**
     * This autonomous (along with the chooser code above) shows how to select
     * between different autonomous modes using the dashboard. The sendable
     * chooser code works with the Java SmartDashboard. If you prefer the
     * LabVIEW Dashboard, remove all of the chooser code and uncomment the
     * getString line to get the auto name from the text box below the Gyro
     *
     * You can add additional auto modes by adding additional comparisons to the
     * switch structure below with additional strings. If using the
     * SendableChooser make sure to add them to the chooser code above as well.
     */
    @Override
    public void autonomousInit() {
        log.info("Entered autonomousInit()");

        CommandGroup selectedAuton = smartDashboardHandler.getAutonCommand();
        log.info("Starting auto command: " + selectedAuton.getName());
        selectedAuton.start();
    }

    /** This function is called periodically during autonomous. */
    @Override
    public void autonomousPeriodic() {
        // TODO Move to custom timer
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        log.info("Entered teleopInit()");
    }

    /** This function is called periodically during operator control. */
    @Override
    public void teleopPeriodic() {
        // TODO Move to custom timer
        Scheduler.getInstance().run();
    }

    /** This function is called periodically during test mode. */
    @Override
    public void testPeriodic() {
    }

    @Override
    public void disabledInit() {
        log.info("Entered disabledInit()");
    }
}
