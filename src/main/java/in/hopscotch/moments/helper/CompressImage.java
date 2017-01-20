package in.hopscotch.moments.helper;


import java.awt.Image;
import java.io.IOException;

import javax.swing.ImageIcon;

public final class CompressImage {

    private static CompressImage instance;

    private CompressImage() {
    }

    public static synchronized CompressImage getInstance() {
        if (null == instance) {
            instance = new CompressImage();
        }
        return instance;
    }

    public Integer[] compress(String sourcePath, String destPath, Integer width, Integer height, String contentType) throws IOException {
        int realWidth = 0;
        int realHeight = 0;
        Image img = new ImageIcon(sourcePath).getImage();

        Integer sourceWidth = img.getWidth(null);
        Integer sourceHeight = img.getHeight(null);
        if (sourceWidth < width && sourceHeight < height) {
            realWidth = sourceWidth;
            realHeight = sourceHeight;
        } else {
            double rate1 = (width * 1.0) / (sourceWidth * 1.0);
            double rate2 = (height * 1.0) / (sourceHeight * 1.0);
            double rate = rate1 > rate2 ? rate2 : rate1;

            realWidth = (int) Math.ceil(sourceWidth * rate);
            realHeight = (int) Math.ceil(sourceHeight * rate);
        }


        return new Integer[]{realWidth, realHeight};
    }

}