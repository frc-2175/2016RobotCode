package org.usfirst.frc2175.driverstation;

import org.usfirst.frc2175.command.autonomous.DoNothingAutonomous;
import org.usfirst.frc2175.command.autonomous.TestAllFeaturesAutonomous;
import org.usfirst.frc2175.driverstation.autonomous.ChevalDeFriseAutonomous;
import org.usfirst.frc2175.driverstation.autonomous.CrossAndShootAutonomous;
import org.usfirst.frc2175.driverstation.autonomous.CrossAutonomous;
import org.usfirst.frc2175.driverstation.autonomous.CrossTwiceAutonomous;
import org.usfirst.frc2175.driverstation.autonomous.PortcullisAutonomous;
import org.usfirst.frc2175.driverstation.autonomous.ShootTwiceAutonomous;
import org.usfirst.frc2175.driverstation.autonomous.StaticAutonomous;
import org.usfirst.frc2175.driverstation.autonomous.TurnLeftAutonomous;
import org.usfirst.frc2175.driverstation.autonomous.TurnNoneAutonomous;
import org.usfirst.frc2175.driverstation.autonomous.TurnRightAutonomous;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.vision.VisionProcessing;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class AutonomousTraitFactory {

    public SendableChooser findAutonType() {
        final SendableChooser typeOfAuton = new SendableChooser();
        populateAutonTypeChooser(typeOfAuton);
        return typeOfAuton;
    }

    public SendableChooser findOuterworkType() {
        final SendableChooser outerworkType = new SendableChooser();
        populateAutonOuterworkChooser(outerworkType);
        return outerworkType;
    }

    public SendableChooser findAutonAngle() {
        final SendableChooser turnAngle = new SendableChooser();
        populateAutonAngleChooser(turnAngle);
        return turnAngle;

    }

    public SendableChooser extraAutons(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers,
            VisionProcessing visionProcessing) {
        final SendableChooser extraAuton = new SendableChooser();
        populateExtraAutonChooser(extraAuton, robotSubsystems,
                robotControllers);
        return null;

    }

    protected void populateAutonTypeChooser(SendableChooser typeOfAuton) {
        typeOfAuton.addDefault("Do Nothing", new DoNothingAutonomous());
        typeOfAuton.addObject("Cross", new CrossAutonomous());
        typeOfAuton.addObject("Cross Twice", new CrossTwiceAutonomous());
        typeOfAuton.addObject("Cross And Shoot", new CrossAndShootAutonomous());
        typeOfAuton.addObject("Shoot Twice", new ShootTwiceAutonomous());

    }

    protected void populateAutonOuterworkChooser(
            SendableChooser outerworkType) {
        outerworkType.addDefault("Static", new StaticAutonomous());
        outerworkType.addObject("Portcullis", new PortcullisAutonomous());
        outerworkType.addObject("ChevalDeFrise", new ChevalDeFriseAutonomous());

    }

    protected void populateAutonAngleChooser(SendableChooser turnAngle) {
        turnAngle.addDefault("Forwards", new TurnNoneAutonomous());
        turnAngle.addObject("Slight Left", new TurnLeftAutonomous());
        turnAngle.addObject("Slight Right", new TurnRightAutonomous());
    }

    protected void populateExtraAutonChooser(SendableChooser extraAuton,
            RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        extraAuton.addDefault("None", new DoNothingAutonomous());
        extraAuton.addObject("Careful: Test all robot features",
                new TestAllFeaturesAutonomous(robotSubsystems,
                        robotControllers));
    }
}
