package in.hopscotch.moments.util;

import java.awt.Image;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.springframework.util.StringUtils;

import in.hopscotch.moments.api.response.ImageArea;

public class ImageUtil {

    private static final Integer IMAGE_MIN_VALUE = 296;

    public static String getImageSuffix(String fileName) {
        if (!StringUtils.hasText(fileName))
            throw new RuntimeException("Invalid image type.");
        int index = fileName.lastIndexOf('.');
        if (index == -1)
            throw new RuntimeException("Invalid image type.");
        return fileName.substring(index + 1);
    }

    public static String mergeNewImageFileName(String fileName, String suffix) {
        return fileName + "." + suffix;
    }

    public static ImageArea getImageArea(InputStream imgStream) throws IOException {
        Integer width = null;
        Integer height = null;
        try {
            Image img = ImageIO.read(imgStream);
            width = img.getWidth(null);
            height = img.getHeight(null);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return new ImageArea(width, height);
    }

    public static boolean isValidFile(Integer expectedHeight, Integer expectedWidth, ImageArea imageArea) {
        boolean val = false;
        Integer imageHeight = imageArea.getHeight();
        Integer imageWidth = imageArea.getWidth();
        Integer aspectRatioGiven = imageHeight.compareTo(imageWidth);
        Integer aspectRatioExpected = (expectedHeight != null && expectedWidth != null) ? expectedHeight.compareTo(expectedWidth) : null;
        if ((aspectRatioExpected == null)
                || (((expectedHeight.equals(imageHeight) && expectedWidth.equals(imageWidth)) || ((imageHeight >= IMAGE_MIN_VALUE && imageWidth >= IMAGE_MIN_VALUE) && aspectRatioGiven == 0 && aspectRatioExpected == 0)))) {
            val = true;
        }

        return val;
    }

    public static boolean isValidFileStrict(Integer expectedHeight, Integer expectedWidth, ImageArea imageArea) {
        boolean val = false;
        Integer imageHeight = imageArea.getHeight();
        Integer imageWidth = imageArea.getWidth();
        Integer aspectRatioExpected = (expectedHeight != null && expectedWidth != null) ? expectedHeight.compareTo(expectedWidth) : null;
        if (aspectRatioExpected == null || (expectedHeight.equals(imageHeight) && expectedWidth.equals(imageWidth))) {
            val = true;
        }
        return val;
    }

    public static boolean validImageSizeRange(Integer minWidth, Integer maxHeight, ImageArea imageArea) {
        return imageArea.getWidth() >= minWidth && imageArea.getHeight() <= maxHeight;
    }

    public static String getImageSizeRangeErrorMessage(Integer minWidth, Integer maxHeight) {
        StringBuilder message = new StringBuilder();
        if (minWidth > 0) {
            message.append("Image width should be at least " + minWidth + "px. ");
        }
        if (maxHeight < Integer.MAX_VALUE) {
            message.append("Image height should be at most " + maxHeight + "px. ");
        }
        return message.toString();
    }

}