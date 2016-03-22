package org.usfirst.frc2175;

import java.util.logging.Logger;

import org.usfirst.frc2175.command.single.RetractCatapultCommand;
import org.usfirst.frc2175.command.single.ShiftToClimbGearNeutralCommand;
import org.usfirst.frc2175.commandmapper.JoystickEventMapper;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.config.WiringConfig;
import org.usfirst.frc2175.controlloop.CommandSchedulerLoop;
import org.usfirst.frc2175.driverstation.DeadbandCalculator;
import org.usfirst.frc2175.driverstation.DriverStation;
import org.usfirst.frc2175.driverstation.ImageHandler;
import org.usfirst.frc2175.driverstation.SmartDashboardHandler;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

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
            new RobotControllers(robotSubsystems, robotConfig);
    private final JoystickEventMapper joystickEventMapper =
            new JoystickEventMapper(robotConfig, driverStation, robotSubsystems,
                    robotControllers);
    private final SmartDashboardHandler smartDashboardHandler =
            new SmartDashboardHandler(robotSubsystems, robotControllers);

    private final CommandSchedulerLoop commandSchedulerLoop =
            new CommandSchedulerLoop();

    private Ultrasonic ultrasonicSensor;
    private double distanceFromWall;
    private ImageHandler imageHandler;

    // This must come after RobotConfig
    private final Logger log = Logger.getLogger(getClass().getName());

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
        commandSchedulerLoop.start();
        configureSensor();
        // configureCamera();
    }

    protected void configureSensor() {
        WiringConfig wiringConfig = robotConfig.getWiringConfig();
        ultrasonicSensor = wiringConfig.getUltrasonicSensor();
    }

    protected void configureCamera() {
        // CameraServer server = CameraServer.getInstance();
        // VisionProcessingConfig visionProcessingConfig =
        // robotConfig.getVisionProcessingConfig();
        // int webCamQuality = visionProcessingConfig.getWebCamQuality();
        // String webCamName = visionProcessingConfig.getWebCamName();
        // server.setQuality(webCamQuality);
        // server.startAutomaticCapture(webCamName);
        WiringConfig wiringConfig = robotConfig.getWiringConfig();
        imageHandler = new ImageHandler(wiringConfig);
    }

    protected void startCamera() {
        imageHandler.run();
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

        robotSubsystems.getPowertrainSubsystem().resetEncoders();
        robotSubsystems.getPowertrainSubsystem().resetGyro();

        CommandGroup selectedAuton = smartDashboardHandler.getAutonCommand();
        log.info("Starting auto command: " + selectedAuton.getName());
        selectedAuton.start();
    }

    /** This function is called periodically during autonomous. */
    @Override
    public void autonomousPeriodic() {
    }

    @Override
    public void teleopInit() {
        log.info("Entered teleopInit()");
        robotSubsystems.getPowertrainSubsystem().resetEncoders();
        ultrasonicSensor.setAutomaticMode(true);
    }

    /** This function is called periodically during operator control. */
    @Override
    public void teleopPeriodic() {
        distanceFromWall = ultrasonicSensor.getRangeInches();
        System.out.println(distanceFromWall);
    }

    @Override
    public void testInit() {
        new ShiftToClimbGearNeutralCommand(robotSubsystems);
    }

    /** This function is called periodically during test mode. */
    @Override
    public void testPeriodic() {
    }

    @Override
    public void disabledInit() {
        log.info("Entered disabledInit()");
        ultrasonicSensor.setAutomaticMode(false);
        Command retractCatapult = new RetractCatapultCommand(robotSubsystems);
        retractCatapult.start();
    }
}
