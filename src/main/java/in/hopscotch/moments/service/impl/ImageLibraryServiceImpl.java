package in.hopscotch.moments.service.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import in.hopscotch.moments.entity.ImageLibrary;
import in.hopscotch.moments.repository.ImageLibraryRepository;
import in.hopscotch.moments.service.ImageLibraryService;

@Service
public class ImageLibraryServiceImpl implements ImageLibraryService {

    @Inject
    ImageLibraryRepository imageLibraryRepository;

    public ImageLibrary getImageLibrary(String imageId) {
        return imageLibraryRepository.getById(imageId);
    }
    
    public String getImageUrl(String imageId) {
        ImageLibrary library = getImageLibrary(imageId);
        return library == null ? null : library.getImagePath() + "?version=" + library.getVersion();
    }

    public Map<String, ImageLibrary> getImageLibraryMap(List<String> imageIds) {
        Object[] imageIdsArr = imageIds.toArray();
        Map<String, ImageLibrary> mapOfImageIdAndImageLib = imageLibraryRepository.getImageLibraryMap(imageIdsArr);
        return mapOfImageIdAndImageLib;
    }

}
