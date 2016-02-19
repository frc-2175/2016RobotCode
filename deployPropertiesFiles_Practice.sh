#!/bin/bash
FILES=src/properties/practicebot/*
for f in $FILES
do
	scp $f admin@roborio-2175-frc.local:/home/lvuser
done
ssh admin@roborio-2175-frc.local "killall -q netconsole-host || :"