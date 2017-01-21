package in.hopscotch.moments.api;

import javax.inject.Inject;

import in.hopscotch.moments.api.response.PublingChannelResponse;
import in.hopscotch.moments.api.response.PublingFilterResponse;
import in.hopscotch.moments.api.response.PublingPostResponse;
import in.hopscotch.moments.api.response.PublingSourceResponse;
import in.hopscotch.moments.service.PublingService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/hsmoments")
public class PublingController {
    
    @Inject
    PublingService publingService;
    
    @RequestMapping(value = "/publingchannels", method = RequestMethod.GET)
    @ResponseBody
    public PublingChannelResponse getAllChannels() throws Exception {
        PublingChannelResponse response = publingService.getAllChannels();
        return response;
    }

    @RequestMapping(value = "/publingsources", method = RequestMethod.GET)
    @ResponseBody
    public PublingSourceResponse getAllSources() throws Exception {
        PublingSourceResponse response = publingService.getAllSources();
        return response;
    }
    
    @RequestMapping(value = "/publingfilters", method = RequestMethod.GET)
    @ResponseBody
    public PublingFilterResponse getAllFilters() throws Exception {
        PublingFilterResponse response = publingService.getAllFilters();
        return response;
    }
    
    @RequestMapping(value = "/publingposts", method = RequestMethod.GET)
    @ResponseBody
    public PublingPostResponse getAllPosts() throws Exception {
        PublingPostResponse response = publingService.getAllPosts();
        return response;
    }
}
