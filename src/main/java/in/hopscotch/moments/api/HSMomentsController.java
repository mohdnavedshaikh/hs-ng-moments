package in.hopscotch.moments.api;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import in.hopscotch.moments.api.response.HSMomentsResponse;
import in.hopscotch.moments.api.response.IncrementLikeResponse;
import in.hopscotch.moments.service.HSMomentsPageService;
import in.hopscotch.moments.service.HSMomentsService;

@RestController
@RequestMapping("/hsmoments")
public class HSMomentsController {

    @Inject
    HSMomentsPageService hsMomentsPageService;
    @Inject
    HSMomentsService hsMomentsService;

    @RequestMapping(value = "/getNewestHSMomentsPageData", method = RequestMethod.GET)
    @ResponseBody
    public HSMomentsResponse getNewestHSMomentsPageData(@RequestParam(value = "pageNo", required = false) Integer pageNo, @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        return hsMomentsPageService.getHSMomentsPageData(true, null == pageNo ? 1 : pageNo, null == pageSize ? 20 : pageSize);
    }

    /*
     * @RequestMapping(value = "/getPopularHSMomentsPageData", method =
     * RequestMethod.GET)
     * 
     * @ResponseBody public HSMomentsResponse
     * getPopularHSMomentsPageData(@RequestParam(value = "pageNo", required =
     * false) Integer pageNo, @RequestParam(value = "pageSize", required =
     * false) Integer pageSize) { return
     * hsMomentsPageService.getHSMomentsPageData(false, null == pageNo ? 1 :
     * pageNo, null == pageSize ? 20 : pageSize); }
     */

    @RequestMapping(value = "/incrementLike", method = RequestMethod.POST)
    public IncrementLikeResponse incrementLike(@RequestParam(value = "momentsPhotoId", required = true) Long momentsPhotoId) {
        hsMomentsService.incrementLike(momentsPhotoId);
        IncrementLikeResponse incrementLikeResponse = new IncrementLikeResponse();
        incrementLikeResponse.setStatus("success");
        return incrementLikeResponse;
    }

}
