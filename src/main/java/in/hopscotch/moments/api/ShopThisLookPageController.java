package in.hopscotch.moments.api;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import in.hopscotch.moments.api.response.ShopThisLookPageResponse;
import in.hopscotch.moments.service.ShopThisLookPageService;

@RestController
@RequestMapping("/hsmoments")
public class ShopThisLookPageController {
    
    @Inject
    ShopThisLookPageService shopThisLookPageService;
    
    @RequestMapping(value = "/getShopThisLookPage", method = RequestMethod.GET)
    @ResponseBody
    public ShopThisLookPageResponse getShopThisLookPage(@RequestParam(value = "momentsPhotoId", required = true) Long momentsPhotoId) {
        return shopThisLookPageService.getShopThisLookPage(momentsPhotoId);
    }
}
