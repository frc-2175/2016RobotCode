package org.usfirst.frc2175.vision;

import java.util.Comparator;
import java.util.Vector;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ImageType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ImageProcessor {

    // TODO make these properties in VisionProcessingConfig
    private static final float MINIMUM_PARTICLE_AREA = (float) 0.5;
    private static final int HUE_MIN = 101;
    private static final int HUE_MAX = 64;
    private static final int SAT_MIN = 88;
    private static final int SAT_MAX = 255;
    private static final int VAL_MIN = 134;
    private static final int VAL_MAX = 255;

    private static final NIVision.Range HUE_RANGE =
            new NIVision.Range(HUE_MIN, HUE_MAX);
    private static final NIVision.Range SAT_RANGE =
            new NIVision.Range(SAT_MIN, SAT_MAX);
    private static final NIVision.Range VAL_RANGE =
            new NIVision.Range(VAL_MIN, VAL_MAX);

    // This needs to be the FOV of the webcam
    private static final double VIEW_ANGLE = 0;

    private Image binaryImage;

    private int imaqError;

    private NIVision.ParticleFilterCriteria2 criteria[] =
            new NIVision.ParticleFilterCriteria2[1];
    private NIVision.ParticleFilterOptions2 filterOptions =
            new NIVision.ParticleFilterOptions2(0, 0, 1, 1);

    private Scores scores;
    private double LONG_RATIO = 2.22; // Tote long side = 26.9 / Tote height =
                                      // 12.1 = 2.22
    private double SHORT_RATIO = 1.4; // Tote short side = 16.9 / Tote height =
                                      // 12.1 = 1.4
    private double SCORE_MIN = 75.0; // Minimum score to be considered a tote

    public void initProcessing() {
        this.binaryImage = NIVision.imaqCreateImage(ImageType.IMAGE_U8, 0);
        this.criteria[0] = new NIVision.ParticleFilterCriteria2(
                NIVision.MeasurementType.MT_AREA_BY_IMAGE_AREA,
                MINIMUM_PARTICLE_AREA, 100.0, 0, 0);
        criteria[0].lower = MINIMUM_PARTICLE_AREA;
    }

    public void processImage(Image image) {
        // Make an HSV binary map
        NIVision.imaqColorThreshold(binaryImage, image, 255,
                NIVision.ColorMode.HSV, HUE_RANGE, SAT_RANGE, VAL_RANGE);

        // Filter out particles that are too small, then count them
        imaqError = NIVision.imaqParticleFilter4(binaryImage, binaryImage,
                criteria, filterOptions, null);
        int numParticles = NIVision.imaqCountParticles(binaryImage, 1);

        if (numParticles > 0) {
            // Measure particles and sort by particle size
            Vector<ParticleReport> particles = new Vector<ParticleReport>();
            for (int particleIndex =
                    0; particleIndex < numParticles; particleIndex++) {
                ParticleReport par = new ParticleReport();
                par.PercentAreaToImageArea = NIVision.imaqMeasureParticle(
                        binaryImage, particleIndex, 0,
                        NIVision.MeasurementType.MT_AREA_BY_IMAGE_AREA);
                par.Area = NIVision.imaqMeasureParticle(binaryImage,
                        particleIndex, 0, NIVision.MeasurementType.MT_AREA);
                par.BoundingRectTop = NIVision.imaqMeasureParticle(binaryImage,
                        particleIndex, 0,
                        NIVision.MeasurementType.MT_BOUNDING_RECT_TOP);
                par.BoundingRectLeft = NIVision.imaqMeasureParticle(binaryImage,
                        particleIndex, 0,
                        NIVision.MeasurementType.MT_BOUNDING_RECT_LEFT);
                par.BoundingRectBottom = NIVision.imaqMeasureParticle(
                        binaryImage, particleIndex, 0,
                        NIVision.MeasurementType.MT_BOUNDING_RECT_BOTTOM);
                par.BoundingRectRight = NIVision.imaqMeasureParticle(
                        binaryImage, particleIndex, 0,
                        NIVision.MeasurementType.MT_BOUNDING_RECT_RIGHT);
                particles.add(par);
            }
            particles.sort(null);

            // This example only scores the largest particle. Extending to score
            // all particles and choosing the desired one is left as an exercise
            // for the reader. Note that this scores and reports information
            // about a single particle (single L shaped target). To get accurate
            // information
            // about the location of the tote (not just the distance) you will
            // need to correlate two adjacent targets in order to find the true
            // center of the tote.
            scores.aspect = AspectScore(particles.elementAt(0));
            scores.area = AreaScore(particles.elementAt(0));
            boolean isGoal =
                    scores.aspect > SCORE_MIN && scores.area > SCORE_MIN;
            SmartDashboard.putBoolean("Tote found", isGoal);

            // Send distance and tote status to dashboard. The bounding rect,
            // particularly the horizontal center (left - right) may be useful
            // for rotating/driving towards a tote
        } else {
        }

    }

    private class ParticleReport
            implements Comparator<ParticleReport>, Comparable<ParticleReport> {
        double PercentAreaToImageArea;
        double Area;
        double BoundingRectLeft;
        double BoundingRectTop;
        double BoundingRectRight;
        double BoundingRectBottom;

        @Override
        public int compareTo(ParticleReport r) {
            return (int) (r.Area - this.Area);
        }

        @Override
        public int compare(ParticleReport r1, ParticleReport r2) {
            return (int) (r1.Area - r2.Area);
        }
    }

    private class Scores {
        double area;
        double aspect;
    }

    // Comparator function for sorting particles. Returns true if particle 1 is
    // larger
    static boolean CompareParticleSizes(ParticleReport particle1,
            ParticleReport particle2) {
        // we want descending sort order
        return particle1.PercentAreaToImageArea > particle2.PercentAreaToImageArea;
    }

    /**
     * Converts a ratio with ideal value of 1 to a score. The resulting function
     * is piecewise linear going from (0,0) to (1,100) to (2,0) and is 0 for all
     * inputs outside the range 0-2
     */
    double ratioToScore(double ratio) {
        return (Math.max(0, Math.min(100 * (1 - Math.abs(1 - ratio)), 100)));
    }

    double AreaScore(ParticleReport report) {
        double boundingArea =
                (report.BoundingRectBottom - report.BoundingRectTop)
                        * (report.BoundingRectRight - report.BoundingRectLeft);
        // Tape is 7" edge so 49" bounding rect. With 2" wide tape it covers 24"
        // of the rect.
        return ratioToScore((49 / 24) * report.Area / boundingArea);
    }

    /**
     * Method to score if the aspect ratio of the particle appears to match the
     * retro-reflective target. Target is 7"x7" so aspect should be 1
     */
    double AspectScore(ParticleReport report) {
        return ratioToScore(((report.BoundingRectRight
                - report.BoundingRectLeft)
                / (report.BoundingRectBottom - report.BoundingRectTop)));
    }

    /**
     * Computes the estimated distance to a target using the width of the
     * particle in the image. For more information and graphics showing the math
     * behind this approach see the Vision Processing section of the
     * ScreenStepsLive documentation.
     *
     * @param image
     *            The image to use for measuring the particle estimated
     *            rectangle
     * @param report
     *            The Particle Analysis Report for the particle
     * @param isLong
     *            Boolean indicating if the target is believed to be the long
     *            side of a tote
     * @return The estimated distance to the target in feet.
     */
    double computeDistance(Image image, ParticleReport report) {
        double normalizedWidth, targetWidth;
        NIVision.GetImageSizeResult size;

        size = NIVision.imaqGetImageSize(image);
        normalizedWidth =
                2 * (report.BoundingRectRight - report.BoundingRectLeft)
                        / size.width;
        targetWidth = 7;

        return targetWidth / (normalizedWidth * 12
                * Math.tan(VIEW_ANGLE * Math.PI / (180 * 2)));
    }

}
