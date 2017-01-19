package in.hopscotch.moments.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import in.hopscotch.moments.entity.ImageLibrary;
import in.hopscotch.moments.service.ImageLibraryService;

@Component
public class ImageLibraryServiceHelper {

    @Value("${com.nstechs.commerce.file.dns}")
    String fileDns;

    @Inject
    ImageLibraryService imageLibraryService;
    
    public String getImageCDNUrl(String imageId) {
        if (!StringUtils.hasText(imageId)) return null;
        StringBuilder builder = new StringBuilder();
        builder.append("https://").append(fileDns).append("/fstatic").append(imageLibraryService.getImageUrl(imageId));
        return builder.toString();
    }

    public Map<String, String> getMapOfImageIdAndImageCDNUrl(List<String> imageIds) {
        Map<String, String> mapOfImageIdAndImageCDNUrl = new HashMap<String, String>();
        Map<String, ImageLibrary> mapOfImageIdAndImageLib = imageLibraryService.getImageLibraryMap(imageIds);

        for (Map.Entry<String, ImageLibrary> entry : mapOfImageIdAndImageLib.entrySet()) {
            String imageCDNUrl = frameImageCDNUrl(entry.getValue() == null ? null : entry.getValue().getImagePath() + "?version=" + entry.getValue().getVersion());
            mapOfImageIdAndImageCDNUrl.put(entry.getKey(), imageCDNUrl);
        }
        return mapOfImageIdAndImageCDNUrl;
    }

    private String frameImageCDNUrl(String imageUrl) {
        if (!StringUtils.hasText(imageUrl))
            return null;
        StringBuilder builder = new StringBuilder();
        builder.append("https://").append(fileDns).append("/fstatic").append(imageUrl);
        return builder.toString();
    }
}
