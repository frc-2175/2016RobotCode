package org.usfirst.frc2175;

import edu.wpi.first.wpilibj.networktables.NetworkTablesJNI;
import mockit.Mock;
import mockit.MockUp;

public class MockNetworkTablesJNI extends MockUp<NetworkTablesJNI> {
    @Mock
    public void $clinit() {
        System.out.println("Mock $clinit for NetworkTablesJNI called");
    }
}
