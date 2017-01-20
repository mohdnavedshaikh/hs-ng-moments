package in.hopscotch.moments.api;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import in.hopscotch.moments.entity.HSMomentsData;
import in.hopscotch.moments.service.UploadHSMomentsPhotosService;

@RestController
@RequestMapping("/hsmoments")
public class UploadHSMomentsPhotosController {
    
    @Inject
    UploadHSMomentsPhotosService uploadHSMomentsPhotosService;
    
    @RequestMapping(value = "/uploadMomentsPhotos", method = RequestMethod.GET)
    public String getUploadMomentsPage() {
        Integer customerId = 12341234;
        
        return "Success";
    }
    
    @RequestMapping(value = "/uploadMomentsPhotos", method = RequestMethod.POST)
    public String testSpringMicroService() {
        HSMomentsData hsMomentsData = new HSMomentsData();
        // hsMomentsData.setId(1L);
        hsMomentsData.setHsImageURL("testimageurl");
        hsMomentsData.setDescription("test");
        uploadHSMomentsPhotosService.uploadMomentData(hsMomentsData);
        return "Success";
    }

}
