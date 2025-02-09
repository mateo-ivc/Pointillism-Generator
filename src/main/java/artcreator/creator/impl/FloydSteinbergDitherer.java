package artcreator.creator.impl;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.List;

public class FloydSteinbergDitherer {

    public static BufferedImage dither(BufferedImage image, List<Color> palette) {
        int width = image.getWidth();
        int height = image.getHeight();
        int[][][] error = new int[width][height][3]; // Store error for RGB

        BufferedImage ditheredImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Get original color with error diffusion applied
                Color original = new Color(image.getRGB(x, y));
                int r = clamp(original.getRed() + error[x][y][0]);
                int g = clamp(original.getGreen() + error[x][y][1]);
                int b = clamp(original.getBlue() + error[x][y][2]);

                // Find closest color in palette
                Color nearest = findNearestColor(new Color(r, g, b), palette);
                ditheredImage.setRGB(x, y, nearest.getRGB());

                // Compute error
                int errR = r - nearest.getRed();
                int errG = g - nearest.getGreen();
                int errB = b - nearest.getBlue();

                // Distribute the error (Floyd-Steinberg coefficients)
                diffuseError(error, x + 1, y, errR, errG, errB, 7.0 / 16.0, width, height);
                diffuseError(error, x - 1, y + 1, errR, errG, errB, 3.0 / 16.0, width, height);
                diffuseError(error, x, y + 1, errR, errG, errB, 5.0 / 16.0, width, height);
                diffuseError(error, x + 1, y + 1, errR, errG, errB, 1.0 / 16.0, width, height);
            }
        }
        return ditheredImage;
    }

    private static void diffuseError(int[][][] error, int x, int y, int errR, int errG, int errB, double factor, int width, int height) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            error[x][y][0] += (int) (errR * factor);
            error[x][y][1] += (int) (errG * factor);
            error[x][y][2] += (int) (errB * factor);
        }
    }

    private static Color findNearestColor(Color color, List<Color> palette) {
        Color closest = null;
        double minDistance = Double.MAX_VALUE;

        for (Color c : palette) {
            double distance = colorDistance(color, c);
            if (distance < minDistance) {
                minDistance = distance;
                closest = c;
            }
        }
        return closest;
    }

    private static double colorDistance(Color c1, Color c2) {
        int rDiff = c1.getRed() - c2.getRed();
        int gDiff = c1.getGreen() - c2.getGreen();
        int bDiff = c1.getBlue() - c2.getBlue();
        return Math.sqrt(rDiff * rDiff + gDiff * gDiff + bDiff * bDiff);
    }

    private static int clamp(int value) {
        return Math.max(0, Math.min(255, value));
    }
}
