package in.hopscotch.moments.service;

import java.util.List;
import java.util.Map;

import in.hopscotch.moments.entity.ImageLibrary;

public interface ImageLibraryService {

    ImageLibrary getImageLibrary(boolean isFromHSMoments, String imageId);

    Map<String, ImageLibrary> getImageLibraryMap(boolean isFromHSMoments, List<String> imageIds);

    String getImageUrl(boolean isFromHSMoments, String imageId);

}
