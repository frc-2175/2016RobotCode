package org.usfirst.frc2175.subsystem.vision;

import java.util.logging.Logger;

import org.usfirst.frc2175.config.VisionProcessingConfig;
import org.usfirst.frc2175.util.HighestArrayIndexFinder;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class VisionProcessing {
    private final Logger log = Logger.getLogger(getClass().getName());

    private static final double[] DEFAULT_NUMBER_ARRAY = { 0 };

    private final HighestArrayIndexFinder indexFinder =
            new HighestArrayIndexFinder();;

    private final NetworkTable contourReport;

    private double previousCenterXValue;

    public VisionProcessing(VisionProcessingConfig visionProcessingConfig) {
        String contourReportName =
                visionProcessingConfig.getContourReportName();
        contourReport = NetworkTable.getTable(contourReportName);
    }

    private void updateTable() {
        // TODO figure out how to refresh a networktable
    }

    public double[] getContourCenterX() {
        return contourReport.getNumberArray("centerX", DEFAULT_NUMBER_ARRAY);
    }

    public double[] getContourCenterY() {
        return contourReport.getNumberArray("centerY", DEFAULT_NUMBER_ARRAY);
    }

    public double[] getContourWidth() {
        return contourReport.getNumberArray("width", DEFAULT_NUMBER_ARRAY);
    }

    public double getLargestContourCenterX() {
        updateTable();
        double[] contourWidths;
        double[] contourCenterXs;
        synchronized (this) {
            contourWidths = getContourWidth();
            contourCenterXs = getContourCenterX();
        }

        final double value;

        int largestContourIndex =
                indexFinder.determineLargestArrayItemIndex(contourWidths);

        if (largestContourIndex == HighestArrayIndexFinder.NO_VALUES) {
            value = HighestArrayIndexFinder.NO_VALUES;
        } else if (contourWidths.length != contourCenterXs.length) {
            log.warning("Center X arrays weren't the same length!"
                    + " Using previous value of " + previousCenterXValue
                    + ". contourCenterXs.length=" + contourCenterXs.length
                    + "; contourWidths.length=" + contourWidths.length);
            value = previousCenterXValue;
        } else {
            value = contourCenterXs[largestContourIndex];
            previousCenterXValue = value;
        }

        log.info("Center X value returned: " + value);
        return value;
    }
}
