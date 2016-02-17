#!/bin/bash
# First transfer over the files
scp src/properties/competitionbot/manipulator.properties src/properties/competitionbot/catapultShooter.properties src/properties/competitionbot/gamepad.properties src/properties/competitionbot/intake.properties src/properties/competitionbot/joysticks.properties src/properties/competitionbot/vision.properties src/properties/competitionbot/wiring.properties src/properties/competitionbot/ControlLoopConstants.properties admin@roborio-2175-frc.local:/home/lvuser

# Then restart robot code
ssh admin@roborio-2175-frc.local "killall -q netconsole-host || :"