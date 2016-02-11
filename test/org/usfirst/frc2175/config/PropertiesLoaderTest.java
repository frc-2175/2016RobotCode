package org.usfirst.frc2175.config;

import org.junit.Test;
import org.usfirst.frc2175.TestBase;

public class PropertiesLoaderTest extends TestBase {
    @Test
    public void testPropertiesLoader_Competition() {
        BaseConfig.setPropertyFileDir(PROPERTY_FILE_DIR_SRC_COMPETITION);
        PropertiesLoader sut = new PropertiesLoader();
    }

    @Test
    public void testPropertiesLoader_Practice() {
        BaseConfig.setPropertyFileDir(PROPERTY_FILE_DIR_SRC_PRACTICE);
        PropertiesLoader sut = new PropertiesLoader();
    }
}
