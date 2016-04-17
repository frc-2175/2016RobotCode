package org.usfirst.frc2175.driverstation;

import org.usfirst.frc2175.command.autonomous.CrossChevalDeFriseAndShootForwardAutonomous;
import org.usfirst.frc2175.command.autonomous.CrossChevalDeFriseAndShootSlightLeftAutonomous;
import org.usfirst.frc2175.command.autonomous.CrossChevalDeFriseAndShootSlightRightAutonomous;
import org.usfirst.frc2175.command.autonomous.CrossChevalDeFriseAutonomous;
import org.usfirst.frc2175.command.autonomous.CrossPortcullisAndShootForwardAutonomous;
import org.usfirst.frc2175.command.autonomous.CrossPortcullisAndShootSlightLeftAutonomous;
import org.usfirst.frc2175.command.autonomous.CrossPortcullisAndShootSlightRightAutonomous;
import org.usfirst.frc2175.command.autonomous.CrossPortcullisAutonomous;
import org.usfirst.frc2175.command.autonomous.CrossStaticDefenseAndShootForwardAutonomous;
import org.usfirst.frc2175.command.autonomous.CrossStaticDefenseAndShootSlightLeftAutonomous;
import org.usfirst.frc2175.command.autonomous.CrossStaticDefenseAndShootSlightRightAutonomous;
import org.usfirst.frc2175.command.autonomous.CrossStaticDefenseNoEncoderAutonomous;
import org.usfirst.frc2175.command.autonomous.CrossTwiceChevalDeFriseAutonomous;
import org.usfirst.frc2175.command.autonomous.CrossTwicePortcullisAutonomous;
import org.usfirst.frc2175.command.autonomous.CrossTwiceStaticDefenseAutonomous;
import org.usfirst.frc2175.command.autonomous.DoNothingAutonomous;
import org.usfirst.frc2175.command.autonomous.TestAllFeaturesAutonomous;
import org.usfirst.frc2175.command.autonomous.WeakenShootLowBarAutonomous;
import org.usfirst.frc2175.command.autonomous.block.CrossStaticDefenseBlock;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.vision.VisionProcessing;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class AutonomousChooserFactory {
    public SendableChooser make(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers,
            VisionProcessing visionProcessing) {
        final SendableChooser autonChooser = new SendableChooser();

        populateAutonChooser(autonChooser, robotSubsystems, robotControllers,
                visionProcessing);

        return autonChooser;
    }

    protected void populateAutonChooser(SendableChooser autonChooser,
            RobotSubsystems robotSubsystems, RobotControllers robotControllers,
            VisionProcessing visionProcessing) {
        autonChooser.addDefault("Do nothing", new DoNothingAutonomous());

        // list cross ones first as probably most common
        addCrossAutons(autonChooser, robotSubsystems, robotControllers,
                visionProcessing);
        addCrossTwiceAutons(autonChooser, robotSubsystems, robotControllers);

        autonChooser.addObject("Careful: Test all robot features",
                new TestAllFeaturesAutonomous(robotSubsystems,
                        robotControllers));
    }

    // WARNING: keep these same order as Damage ones for driver clarity
    protected void addCrossAutons(SendableChooser autonChooser,
            RobotSubsystems robotSubsystems, RobotControllers robotControllers,
            VisionProcessing visionProcessing) {
        autonChooser.addObject("Cross simple defense",
                new CrossStaticDefenseBlock(robotSubsystems, robotControllers,
                        true));
        autonChooser.addObject("Cross simple defense and shoot forward",
                new CrossStaticDefenseAndShootForwardAutonomous(robotSubsystems,
                        robotControllers, visionProcessing));
        autonChooser.addObject("Cross simple defense and shoot slight left",
                new CrossStaticDefenseAndShootSlightLeftAutonomous(
                        robotSubsystems, robotControllers, visionProcessing));
        autonChooser.addObject("Cross simple defense and shoot slight right",
                new CrossStaticDefenseAndShootSlightRightAutonomous(
                        robotSubsystems, robotControllers, visionProcessing));
        autonChooser.addObject("Cross cheval de frise",
                new CrossChevalDeFriseAutonomous(robotSubsystems,
                        robotControllers));
        autonChooser.addObject("Cross ChevalDeFrise and shoot forward",
                new CrossChevalDeFriseAndShootForwardAutonomous(robotSubsystems,
                        robotControllers, visionProcessing));
        autonChooser.addObject("Cross ChevalDeFrise and shoot slight left",
                new CrossChevalDeFriseAndShootSlightLeftAutonomous(
                        robotSubsystems, robotControllers, visionProcessing));
        autonChooser.addObject("Cross ChevalDeFrise and shoot slight right",
                new CrossChevalDeFriseAndShootSlightRightAutonomous(
                        robotSubsystems, robotControllers, visionProcessing));
        autonChooser.addObject("Cross portcullis",
                new CrossPortcullisAutonomous(robotSubsystems,
                        robotControllers));
        autonChooser.addObject("Cross portcullis and shoot forward",
                new CrossPortcullisAndShootForwardAutonomous(robotSubsystems,
                        robotControllers, visionProcessing));
        autonChooser.addObject("Cross portcullis and shoot slight left",
                new CrossPortcullisAndShootSlightLeftAutonomous(robotSubsystems,
                        robotControllers, visionProcessing));
        autonChooser.addObject("Cross portcullis and shoot slight right",
                new CrossPortcullisAndShootSlightRightAutonomous(
                        robotSubsystems, robotControllers, visionProcessing));
        autonChooser.addObject("Cross simple defense - no encoders",
                new CrossStaticDefenseNoEncoderAutonomous(robotSubsystems,
                        robotControllers));
        autonChooser.addObject("Weaken shoot low bar",
                new WeakenShootLowBarAutonomous(robotSubsystems,
                        robotControllers, visionProcessing));
    }

    // WARNING: keep these same order as Weaken ones for driver clarity
    protected void addCrossTwiceAutons(SendableChooser autonChooser,
            RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        autonChooser.addObject("Cross twice simple defense",
                new CrossTwiceStaticDefenseAutonomous(robotSubsystems,
                        robotControllers));
        autonChooser.addObject("Cross twice cheval de frise",
                new CrossTwiceChevalDeFriseAutonomous(robotSubsystems,
                        robotControllers));
        autonChooser.addObject("Cross twice portcullis",
                new CrossTwicePortcullisAutonomous(robotSubsystems,
                        robotControllers));
        // autonChooser.addObject("Damage shoot low bar",
        // new DamageShootLowBarAutonomous(robotSubsystems,
        // robotControllers));
        // autonChooser.addObject("Damage shoot cheval de frise",
        // new DamageShootChevalDeFriseAutonomous(robotSubsystems,
        // robotControllers));
        // autonChooser.addObject("Damage shoot portcullis",
        // new DamageShootPortcullisAutonomous(robotSubsystems,
        // robotControllers));
        // autonChooser.addObject("Damage shoot rock wall",
        // new DamageShootRockWallAutonomous(robotSubsystems,
        // robotControllers));
    }
}
