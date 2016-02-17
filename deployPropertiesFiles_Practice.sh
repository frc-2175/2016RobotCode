#!/bin/bash
# First transfer over the files
scp src/properties/practicebot/manipulator.properties src/properties/practicebot/catapultShooter.properties src/properties/practicebot/gamepad.properties src/properties/practicebot/intake.properties src/properties/practicebot/joysticks.properties src/properties/practicebot/vision.properties src/properties/practicebot/wiring.properties src/properties/practicebot/ControlLoopConstants.properties admin@roborio-2175-frc.local:/home/lvuser

# Then restart robot code
ssh admin@roborio-2175-frc.local "killall -q netconsole-host || :"