package org.usfirst.frc2175.subsystem.vision;

import java.util.logging.Logger;

import org.usfirst.frc2175.config.ControlLoopConfig;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.config.VisionProcessingConfig;
import org.usfirst.frc2175.util.HighestArrayIndexFinder;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class VisionProcessing {
    private final Logger log = Logger.getLogger(getClass().getName());

    protected static final double[] DEFAULT_NUMBER_ARRAY = { 0 };

    private final HighestArrayIndexFinder indexFinder =
            new HighestArrayIndexFinder();

    private final NetworkTable contourReport;

    private final double cameraFov;
    private final double cameraHorizontalRes;
    private final double centerCamera;

    private double previousCenterXValue;

    public VisionProcessing(RobotConfig robotConfig) {
        VisionProcessingConfig visionProcessingConfig =
                robotConfig.getVisionProcessingConfig();
        String contourReportName =
                visionProcessingConfig.getContourReportName();
        this.contourReport = NetworkTable.getTable(contourReportName);

        ControlLoopConfig controlLoopConfig =
                robotConfig.getControlLoopConfig();
        this.centerCamera = controlLoopConfig.getVisionTurnPID_centerCamera();

        this.cameraFov = visionProcessingConfig.getCameraFOV();
        this.cameraHorizontalRes =
                visionProcessingConfig.getCameraHorizontalRes();
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

    /**
     * @return The largest width's center X value or the prior value if an
     *         "uneven read condition" occurred or
     *         HighestArrayIndexFinder.NO_VALUES when no objects seen in vision
     *         processing. Be sure to check for and handle NO_VALUES to prevent
     *         problems.
     */
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
                    + " Using previous value=" + previousCenterXValue
                    + ". contourCenterXs.length=" + contourCenterXs.length
                    + "; contourWidths.length=" + contourWidths.length);
            value = previousCenterXValue;
        } else {
            value = contourCenterXs[largestContourIndex];
            previousCenterXValue = value;
        }

        log.info("Largest center X value=" + value);
        return value;
    }

    private double getGoalDistanceFromCenterInPixels() {
        final double distance;

        double centerX = getLargestContourCenterX();
        if (centerX == HighestArrayIndexFinder.NO_VALUES) {
            distance = 0;
        } else {
            distance = centerX - centerCamera;
        }

        return distance;
    }

    public double getGoalDistanceFromCenterInDegrees() {
        double degreesPerPixel = cameraFov / cameraHorizontalRes;
        return getGoalDistanceFromCenterInPixels() * degreesPerPixel;
    }
}
