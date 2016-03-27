package org.usfirst.frc2175.driverstation;

import org.usfirst.frc2175.command.autonomous.DamageChevalDeFriseAutonomous;
import org.usfirst.frc2175.command.autonomous.DamagePortcullisAutonomous;
import org.usfirst.frc2175.command.autonomous.DamageShootChevalDeFriseAutonomous;
import org.usfirst.frc2175.command.autonomous.DamageShootLowBarAutonomous;
import org.usfirst.frc2175.command.autonomous.DamageShootPortcullisAutonomous;
import org.usfirst.frc2175.command.autonomous.DamageShootTwoThreeOrFourPositionSimpleDefenseAutonomous;
import org.usfirst.frc2175.command.autonomous.DamageSimpleDefenseAutonomous;
import org.usfirst.frc2175.command.autonomous.DoNothingAutonomous;
import org.usfirst.frc2175.command.autonomous.TestAllFeaturesAutonomous;
import org.usfirst.frc2175.command.autonomous.WeakenChevalDeFriseAutonomous;
import org.usfirst.frc2175.command.autonomous.WeakenPortcullisAutonomous;
import org.usfirst.frc2175.command.autonomous.WeakenShootChevalDeFriseAutonomous;
import org.usfirst.frc2175.command.autonomous.WeakenShootLowBarAutonomous;
import org.usfirst.frc2175.command.autonomous.WeakenShootPortcullisAutonomous;
import org.usfirst.frc2175.command.autonomous.WeakenShootTwoThreeOrFourPositionSimpleDefenseAutonomous;
import org.usfirst.frc2175.command.autonomous.WeakenSimpleDefenseAutonomous;
import org.usfirst.frc2175.command.autonomous.WeakenSimpleDefenseNoEncoderAutonomous;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class AutonomousChooserFactory {
    public SendableChooser make(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        final SendableChooser autonChooser = new SendableChooser();

        populateAutonChooser(autonChooser, robotSubsystems, robotControllers);

        return autonChooser;
    }

    protected void populateAutonChooser(SendableChooser autonChooser,
            RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        autonChooser.addDefault("Do nothing", new DoNothingAutonomous());

        // list weaken ones first as probably most common
        addWeakenAutons(autonChooser, robotSubsystems, robotControllers);
        addDamageAutons(autonChooser, robotSubsystems, robotControllers);

        autonChooser.addObject("Careful: Test all robot features",
                new TestAllFeaturesAutonomous(robotSubsystems,
                        robotControllers));
    }

    // WARNING: keep these same order as Damage ones for driver clarity
    protected void addWeakenAutons(SendableChooser autonChooser,
            RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        autonChooser.addObject("Weaken simple defense - no encoders",
                new WeakenSimpleDefenseNoEncoderAutonomous(robotSubsystems,
                        robotControllers));
        autonChooser.addObject("Weaken simple defense",
                new WeakenSimpleDefenseAutonomous(robotSubsystems,
                        robotControllers));
        autonChooser.addObject("Weaken cheval de frise",
                new WeakenChevalDeFriseAutonomous(robotSubsystems,
                        robotControllers));
        autonChooser.addObject("Weaken portcullis",
                new WeakenPortcullisAutonomous(robotSubsystems,
                        robotControllers));
        autonChooser.addObject("Weaken shoot low bar",
                new WeakenShootLowBarAutonomous(robotSubsystems,
                        robotControllers));
        autonChooser.addObject("Weaken shoot cheval de frise",
                new WeakenShootChevalDeFriseAutonomous(robotSubsystems,
                        robotControllers));
        autonChooser.addObject("Weaken shoot porticullis",
                new WeakenShootPortcullisAutonomous(robotSubsystems,
                        robotControllers));
        autonChooser.addObject(
                "Weaken shoot 2nd, 3rd, or 4th position simple defense",
                new WeakenShootTwoThreeOrFourPositionSimpleDefenseAutonomous(
                        robotSubsystems, robotControllers));
    }

    // WARNING: keep these same order as Weaken ones for driver clarity
    protected void addDamageAutons(SendableChooser autonChooser,
            RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        autonChooser.addObject("Damage simple defense",
                new DamageSimpleDefenseAutonomous(robotSubsystems,
                        robotControllers));
        autonChooser.addObject("Damage cheval de frise",
                new DamageChevalDeFriseAutonomous(robotSubsystems,
                        robotControllers));
        autonChooser.addObject("Damage portcullis",
                new DamagePortcullisAutonomous(robotSubsystems,
                        robotControllers));
        autonChooser.addObject("Damage shoot low bar",
                new DamageShootLowBarAutonomous(robotSubsystems,
                        robotControllers));
        autonChooser.addObject("Damage shoot cheval de frise",
                new DamageShootChevalDeFriseAutonomous(robotSubsystems,
                        robotControllers));
        autonChooser.addObject("Damage shoot portcullis",
                new DamageShootPortcullisAutonomous(robotSubsystems,
                        robotControllers));
        autonChooser.addObject(
                "Damage shoot 2nd, 3rd, or 4th position simple defense",
                new DamageShootTwoThreeOrFourPositionSimpleDefenseAutonomous(
                        robotSubsystems, robotControllers));
    }
}
