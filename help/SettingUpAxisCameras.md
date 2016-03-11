# Setting up axis cameras for the robot
*By Max Haland*

While setting up axis cameras may seem scary, it's actually really simple! There are just a few main steps:

1. Connect the camera to power and to the radio
2. Use the provided "Axis Camera Tool" to set up the camera
3. Open the camera in the smart dashboard
4. Go crazy!

This guide will take a more in-depth look at each of these steps.

#### 1. Wiring the camera
The first step in setting up the camera is giving it power and signal. To give the camera signal, just plug it into the radio with a standard ethernet cord. To power the camera, cut the DC power adapter off of the original cord. Strip the wires, and plug it into the 5v section of the breakout on the robot. The camera should boot up and start flashing lights.

#### 2. Configuring the camera for the robot
Open up the "Axis Camera Tool", located [here][tool]. The tool will provide instructions for configuring the camera. If you plan on using the camera for vision tracking, it's ready to go! if not, you will need to access the camera's online configuration page and change the image parameters to reasonable values.

#### 3. Open the camera in the SmartDashboard
In SmartDashboard, navigate to "View > Add... > Simple Camera Viewer". Right click on the pink box that says "NO CONNECTION." Right-click on it and change the camera IP address to whatever address the camera got set to. If you can't right click on the box, press "Ctrl+E" to switch to edit mode. In edit mode, you can also resize the camera window.

#### 4. Go crazy!
Now that you know how to set up cameras, you know as much as I do! Experiment with connecting multiple cameras, connecting cameras through an old D-Link as a switch, or doing anything else you think will be useful! We need to figure out how to connect more than one axis camera, so that would be a good place to start.

[tool]: file:///C:/Program%20Files%20(x86)/National%20Instruments/LabVIEW%202015/project/Axis%20Camera%20Tool/AxisCameraSetup.exe. 
