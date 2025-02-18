package artcreator.creator.impl;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

public class MedianCutColorGenerator {
    public static List<Color> extractColors(BufferedImage image, int n) {
        int width = image.getWidth();
        int height = image.getHeight();

        // Collect all pixels
        List<Color> pixels = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixels.add(new Color(image.getRGB(x, y)));
            }
        }

        // Get representative colors using Median Cut
        return medianCut(pixels, n);
    }

    private static List<Color> medianCut(List<Color> pixels, int n) {
        Queue<List<Color>> colorQueue = new LinkedList<>();
        colorQueue.add(pixels);

        while (colorQueue.size() < n && !colorQueue.isEmpty()) {
            List<Color> current = colorQueue.poll();
            if (current.size() <= 1) continue;

            // Find the channel with the highest range
            int[] min = {255, 255, 255};
            int[] max = {0, 0, 0};

            for (Color c : current) {
                min[0] = Math.min(min[0], c.getRed());
                min[1] = Math.min(min[1], c.getGreen());
                min[2] = Math.min(min[2], c.getBlue());

                max[0] = Math.max(max[0], c.getRed());
                max[1] = Math.max(max[1], c.getGreen());
                max[2] = Math.max(max[2], c.getBlue());
            }

            int maxRangeIndex = 0; // 0 = Red, 1 = Green, 2 = Blue
            int maxRange = max[0] - min[0];
            if ((max[1] - min[1]) > maxRange) {
                maxRangeIndex = 1;
                maxRange = max[1] - min[1];
            }
            if ((max[2] - min[2]) > maxRange) {
                maxRangeIndex = 2;
            }

            // Sort pixels by the channel with the highest range
            int finalMaxRangeIndex = maxRangeIndex;
            current.sort(Comparator.comparingInt(c -> getColorChannel(c, finalMaxRangeIndex)));

            // Split list at the median
            int medianIndex = current.size() / 2;
            List<Color> firstHalf = new ArrayList<>(current.subList(0, medianIndex));
            List<Color> secondHalf = new ArrayList<>(current.subList(medianIndex, current.size()));

            colorQueue.add(firstHalf);
            colorQueue.add(secondHalf);
        }

        // Compute the average color of each bucket
        List<Color> result = new ArrayList<>();
        for (List<Color> group : colorQueue) {
            result.add(averageColor(group));
        }
        return result;
    }

    private static int getColorChannel(Color c, int index) {
        return switch (index) {
            case 0 -> c.getRed();
            case 1 -> c.getGreen();
            case 2 -> c.getBlue();
            default -> throw new IllegalArgumentException("Invalid color index");
        };
    }

    private static Color averageColor(List<Color> colors) {
        int r = 0, g = 0, b = 0;
        for (Color c : colors) {
            r += c.getRed();
            g += c.getGreen();
            b += c.getBlue();
        }
        int size = colors.size();
        return new Color(r / size, g / size, b / size);
    }

    private static Color findClosestColor(Color original, List<Color> palette) {
        Color closestColor = palette.get(0);
        int minDistance = colorDistance(original, closestColor);

        for (Color color : palette) {
            int dist = colorDistance(original, color);
            if (dist < minDistance) {
                minDistance = dist;
                closestColor = color;
            }
        }
        return closestColor;
    }

    private static int colorDistance(Color c1, Color c2) {
        int rDiff = c1.getRed() - c2.getRed();
        int gDiff = c1.getGreen() - c2.getGreen();
        int bDiff = c1.getBlue() - c2.getBlue();
        return rDiff * rDiff + gDiff * gDiff + bDiff * bDiff; // Squared Euclidean distance
    }
}

