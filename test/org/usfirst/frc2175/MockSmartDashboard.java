package org.usfirst.frc2175;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import mockit.Mock;
import mockit.MockUp;

public class MockSmartDashboard extends MockUp<SmartDashboard> {
    @Mock
    public void $clinit() {
        // do nothing
    }

    @Mock
    public static void putData(String key, Sendable data) {
        // do nothing
    }

    @Mock
    public static void putString(String key, String value) {
        // do nothing
    }
}
