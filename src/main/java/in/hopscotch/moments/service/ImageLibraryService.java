package in.hopscotch.moments.service;

import java.util.List;
import java.util.Map;

import in.hopscotch.moments.entity.ImageLibrary;

public interface ImageLibraryService {

    ImageLibrary getImageLibrary(String imageId);

    Map<String, ImageLibrary> getImageLibraryMap(List<String> imageIds);

    String getImageUrl(String imageId);

}
