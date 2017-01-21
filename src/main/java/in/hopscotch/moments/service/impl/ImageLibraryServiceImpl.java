package in.hopscotch.moments.service.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import in.hopscotch.moments.entity.ImageLibrary;
import in.hopscotch.moments.repository.ImageLibraryRepository;
import in.hopscotch.moments.service.ImageLibraryService;

@Service
@Transactional
public class ImageLibraryServiceImpl implements ImageLibraryService {

    @Inject
    ImageLibraryRepository imageLibraryRepository;

    public ImageLibrary getImageLibrary(boolean isFromHSMoments, String imageId) {
        return imageLibraryRepository.getById(isFromHSMoments, imageId);
    }

    public String getImageUrl(boolean isFromHSMoments, String imageId) {
        ImageLibrary library = getImageLibrary(isFromHSMoments, imageId);
        return library == null ? null : library.getImagePath() + "?version=" + library.getVersion();
    }

    public Map<String, ImageLibrary> getImageLibraryMap(boolean isFromHSMoments, List<String> imageIds) {
        Object[] imageIdsArr = imageIds.toArray();
        Map<String, ImageLibrary> mapOfImageIdAndImageLib = imageLibraryRepository.getImageLibraryMap(isFromHSMoments, imageIdsArr);
        return mapOfImageIdAndImageLib;
    }

}
