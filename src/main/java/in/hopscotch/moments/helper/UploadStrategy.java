package in.hopscotch.moments.helper;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Value;

import in.hopscotch.moments.api.request.ImageFileRequest;
import in.hopscotch.moments.api.response.ImageResponse;
import in.hopscotch.moments.db.util.JPAAccess;
import in.hopscotch.moments.entity.ImageLibrary;
import in.hopscotch.moments.util.S3Client;

public abstract class UploadStrategy {

    protected JPAAccess jpaAccess;

    protected S3Client s3Client;

    @Value("${com.nstechs.commerce.fileupload.hsmoments}")
    protected String hsMoments = "/hsmoments";

    @Value("${filesystem.path}")
    protected String FILESYSTEMPATH = "/tmp/hs/nfs/fstatic";

    public void setS3Client(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public void setJpaAccess(JPAAccess jpaAccess) {
        this.jpaAccess = jpaAccess;
    }

    protected String getBigFile(String filePath) {
        return filePath.replaceAll("_full", "_large");
    }

    protected String getMediumFile(String filePath) {
        return filePath.replaceAll("_full", "_medium");
    }

    protected String getSmallFile(String filePath) {
        return filePath.replaceAll("_full", "_thumbnail");
    }

    public ImageLibrary uploadImage(ImageFileRequest imageFileRequest, Date sysdate) throws IOException {
        imageFileRequest.setCutOutImage(false);
        OutputStream targetStream = null;
        String modelName = imageFileRequest.getModelName();
        InputStream uploadStream = imageFileRequest.getInputStream();
        final String dbPath = hsMoments;
        final String dbAbsolutePath = dbPath + "/" + DateUtils.formatDate(sysdate, "yyyyMM") + "/" + imageFileRequest.getFileName();
        final String absolutePath = FILESYSTEMPATH + dbAbsolutePath;
        try {
            File file = new File(absolutePath);
            file.getParentFile().mkdirs();
            targetStream = new FileOutputStream(file);
            byte[] cache = new byte[50];
            int count = uploadStream.read(cache);
            while (count != -1) {
                targetStream.write(cache, 0, count);
                count = uploadStream.read(cache);
            }
            targetStream.flush();

        } catch (IOException e) {
            throw new RuntimeException("File Upload failed.", e);
        } finally {
            if (null != targetStream)
                targetStream.close();
            if (null != uploadStream)
                uploadStream.close();
        }
        Integer[] widthAndHeight = getImgInfo(absolutePath);
        ImageLibrary imageLibrary = new ImageLibrary();
        imageLibrary.setImageId(imageFileRequest.getImageId());
        imageLibrary.setModelName(modelName);
        imageLibrary.setImageName(imageFileRequest.getFileName());
        imageLibrary.setImagePath(dbAbsolutePath);
        imageLibrary.setUploadDate(sysdate);
        imageLibrary.setUseTimes(0);
        imageLibrary.setFullSize(widthAndHeight[0] + "," + widthAndHeight[1]);
        return imageLibrary;
    }

    /**
     * get image width and height
     * 
     * @param absolutePath
     * @throws IOException
     */
    public Integer[] getImgInfo(String absolutePath) throws IOException {
        InputStream imgStream = null;
        Integer width = null;
        Integer height = null;
        try {
            imgStream = new FileInputStream(absolutePath);
            Image img = ImageIO.read(imgStream);
            width = img.getWidth(null);
            height = img.getHeight(null);
            img.flush();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            if (null != imgStream) {
                imgStream.close();
            }
        }
        return new Integer[] { width, height };
    }

    public abstract ImageResponse uploadImageFile(ImageFileRequest imageFileRequest, Date sysdate) throws IOException;

    // public ImageResponse uploadImageFile(Integer productId, ImageFileRequest
    // imageFileRequest, Date sysdate) throws IOException {
    // return null;
    // }

    public void uploadFileToS3(String imagePath) {
        String absolutePath = FILESYSTEMPATH + imagePath;
        File fullImage = new File(absolutePath);
        if (fullImage.exists()) {
            s3Client.uploadFile(imagePath, fullImage);
            fullImage.delete();
        }
        File lagrgeImage = new File(absolutePath.replaceAll("_full", "_large"));
        if (lagrgeImage.exists()) {
            s3Client.uploadFile(imagePath.replaceAll("_full", "_large"), lagrgeImage);
            lagrgeImage.delete();
        }
        File mediumImage = new File(absolutePath.replaceAll("_full", "_medium"));
        if (mediumImage.exists()) {
            s3Client.uploadFile(imagePath.replaceAll("_full", "_medium"), mediumImage);
            mediumImage.delete();
        }
        File thumbnailImage = new File(absolutePath.replaceAll("_full", "_thumbnail"));
        if (thumbnailImage.exists()) {
            s3Client.uploadFile(imagePath.replaceAll("_full", "_thumbnail"), thumbnailImage);
            thumbnailImage.delete();
        }
    }
}