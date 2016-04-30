package org.usfirst.frc2175;

import java.util.logging.Logger;

import org.usfirst.frc2175.command.autonomous.DoNothingAutonomous;
import org.usfirst.frc2175.command.single.RetractCatapultCommand;
import org.usfirst.frc2175.command.single.RunRollerbarIntakeAtSpeedCommand;
import org.usfirst.frc2175.command.single.ShiftToClimbGearNeutralCommand;
import org.usfirst.frc2175.commandmapper.JoystickEventMapper;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.config.VisionProcessingConfig;
import org.usfirst.frc2175.config.WiringConfig;
import org.usfirst.frc2175.controlloop.CommandSchedulerLoop;
import org.usfirst.frc2175.driverstation.DeadbandCalculator;
import org.usfirst.frc2175.driverstation.DriverStation;
import org.usfirst.frc2175.driverstation.ImageHandler;
import org.usfirst.frc2175.driverstation.SmartDashboardHandler;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.sensor.DistanceSensor;
import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.powertrain.PowertrainSubsystem;
import org.usfirst.frc2175.subsystem.shooter.CatapultShooterSubsystem;
import org.usfirst.frc2175.subsystem.shooter.ShotType;
import org.usfirst.frc2175.subsystem.vision.PhotonCannonSubsystem;
import org.usfirst.frc2175.subsystem.vision.VisionProcessing;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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

    private final VisionProcessing visionProcessing =
            new VisionProcessing(robotConfig);

    private final RobotSubsystems robotSubsystems =
            new RobotSubsystems(robotConfig, driverStation, visionProcessing);
    private final RobotControllers robotControllers = new RobotControllers(
            robotSubsystems, robotConfig, visionProcessing);
    private final JoystickEventMapper joystickEventMapper =
            new JoystickEventMapper(robotConfig, driverStation, robotSubsystems,
                    robotControllers, visionProcessing);
    private final SmartDashboardHandler smartDashboardHandler =
            new SmartDashboardHandler(robotSubsystems, robotControllers,
                    visionProcessing);

    private final PowertrainSubsystem powertrainSubsystem =
            robotSubsystems.getPowertrainSubsystem();
    private final PhotonCannonSubsystem photonCannonSubsystem =
            robotSubsystems.getPhotonCannonSubsystem();

    private final CommandSchedulerLoop commandSchedulerLoop =
            new CommandSchedulerLoop();

    private DistanceSensor frontDistanceSensor;

    private ImageHandler imageHandler;

    private CommandGroup selectedAuton = new DoNothingAutonomous();

    // This must come after RobotConfig
    private final Logger log = Logger.getLogger(getClass().getName());

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
        commandSchedulerLoop.start();
        CatapultShooterSubsystem catapultShooterSubsystem =
                robotSubsystems.getCatapultShooterSubsystem();
        catapultShooterSubsystem.setShotType(ShotType.BATTER);
        configureDistanceSensor();
        photonCannonSubsystem.turnLightOn();
        // configureCamera();
        SmartDashboard.putData("Roll Intake In Command",
                new RunRollerbarIntakeAtSpeedCommand(robotSubsystems, 1));
    }

    protected void configureDistanceSensor() {
        WiringConfig wiringConfig = robotConfig.getWiringConfig();
        Ultrasonic ultrasonicSensor = wiringConfig.getUltrasonicSensor();

        frontDistanceSensor = new DistanceSensor(ultrasonicSensor);
    }

    protected void configureCamera() {
        VisionProcessingConfig visionProcessingConfig =
                robotConfig.getVisionProcessingConfig();
        int webCamQuality = visionProcessingConfig.getWebCamQuality();
        String webCamName = visionProcessingConfig.getWebCamName();

        WiringConfig wiringConfig = robotConfig.getWiringConfig();
        imageHandler = new ImageHandler(wiringConfig);

        CameraServer server = CameraServer.getInstance();
        server.setQuality(webCamQuality);
        server.startAutomaticCapture(webCamName);
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

        powertrainSubsystem.resetEncoders();
        powertrainSubsystem.resetGyro();
        photonCannonSubsystem.updateLight();

        frontDistanceSensor.enable();

        selectAndStartAutonomous();

    }

    public void selectAndStartAutonomous() {
        selectedAuton = smartDashboardHandler.getAutonCommand();

        log.info("Starting auto command: " + selectedAuton.getName());
        selectedAuton.start();
    }

    /** This function is called periodically during autonomous. */
    @Override
    public void autonomousPeriodic() {
        updateSmartDashboard();
    }

    @Override
    public void teleopInit() {
        log.info("Entered teleopInit()");

        if (selectedAuton != null) {
            selectedAuton.cancel();
        }
        powertrainSubsystem.resetEncoders();
        photonCannonSubsystem.updateLight();

        frontDistanceSensor.enable();
    }

    /** This function is called periodically during operator control. */
    @Override
    public void teleopPeriodic() {
        updateSmartDashboard();

        // 138-155
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

        frontDistanceSensor.disable();

        Command retractCatapult = new RetractCatapultCommand(robotSubsystems);
        retractCatapult.start();
    }

    private void updateSmartDashboard() {
        frontDistanceSensor.updateDashboard();
        SmartDashboard.putNumber("Gyro Angle",
                powertrainSubsystem.getGyroAngle());
        SmartDashboard.putNumber("Contour CenterX",
                visionProcessing.getLargestContourCenterX());
        SmartDashboard.putNumber("Aim Angle Offset", Math
                .abs(visionProcessing.getGoalDistanceFromCenterInDegrees()));
        SmartDashboard.putNumber("Mean Encoder",
                powertrainSubsystem.getMeanEncoderDistance());
        SmartDashboard.putNumber("Left Encoder",
                powertrainSubsystem.getLeftEncoderDistance());
        SmartDashboard.putNumber("Right Encoder",
                powertrainSubsystem.getRightEncoderDistance());
        if (Math.abs(visionProcessing
                .getGoalDistanceFromCenterInPixels()) < robotConfig
                        .getControlLoopConfig()
                        .getVisionTurnPID_absTolerance()) {
            SmartDashboard.putBoolean("Aim On Target", true);
        } else {
            SmartDashboard.putBoolean("Aim On Target", false);
        }
    }
}
