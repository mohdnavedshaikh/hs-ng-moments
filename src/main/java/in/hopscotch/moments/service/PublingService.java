package in.hopscotch.moments.service;

import in.hopscotch.moments.api.response.PublingChannelResponse;
import in.hopscotch.moments.api.response.PublingFilterResponse;
import in.hopscotch.moments.api.response.PublingPostResponse;
import in.hopscotch.moments.api.response.PublingSourceResponse;

public interface PublingService {

    PublingChannelResponse getAllChannels() throws Exception;
    
    PublingSourceResponse getAllSources() throws Exception;
    
    PublingFilterResponse getAllFilters() throws Exception;
    
    PublingPostResponse getAllPosts() throws Exception;
    
}
