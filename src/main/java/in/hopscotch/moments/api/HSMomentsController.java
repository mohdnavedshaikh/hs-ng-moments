package in.hopscotch.moments.api;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import in.hopscotch.moments.api.response.HSMomentsResponse;
import in.hopscotch.moments.service.HSMomentsPageService;

@RestController
public class HSMomentsController {

    @Inject
    HSMomentsPageService hsMomentsPageService;

    @RequestMapping(value = "/hsmoments/getNewestHSMomentsPageData", method = RequestMethod.GET)
    @ResponseBody
    public HSMomentsResponse getNewestHSMomentsPageData(@RequestParam(value = "pageNo", required = false) Integer pageNo, @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        return hsMomentsPageService.getHSMomentsPageData(true, null == pageNo ? 1 : pageNo, null == pageSize ? 20 : pageSize);
    }

    @RequestMapping(value = "/hsmoments/getPopularHSMomentsPageData", method = RequestMethod.GET)
    @ResponseBody
    public HSMomentsResponse getPopularHSMomentsPageData(@RequestParam(value = "pageNo", required = false) Integer pageNo, @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        return hsMomentsPageService.getHSMomentsPageData(false, null == pageNo ? 1 : pageNo, null == pageSize ? 20 : pageSize);
    }
    
    @RequestMapping(value = "/hsmoments/incrementLike", method = RequestMethod.POST)
    public void incrementLike(@RequestParam() {
        return hsMomentsPageService.getHSMomentsPageData(false, null == pageNo ? 1 : pageNo, null == pageSize ? 20 : pageSize);
    }

}
