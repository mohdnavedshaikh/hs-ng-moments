package in.hopscotch.moments.repository;

import java.util.Map;

import in.hopscotch.moments.entity.ImageLibrary;

public interface ImageLibraryRepository {

    ImageLibrary getById(boolean isFromHSMoments, String imageId);

    Map<String, ImageLibrary> getImageLibraryMap(boolean isFromHSMoments, Object[] params);

}
