package in.hopscotch.moments.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import in.hopscotch.moments.repository.InstagramRepository;
import in.hopscotch.moments.service.InstagramService;

@Service
public class InstagramServiceImpl implements InstagramService {

    @Inject
    InstagramRepository instagramRepository;
    
    @Override
    public void insertOrUpdateAccessToken(String uuId, String accessToken) {
        Integer customerId = instagramRepository.getCustomerIdFromUUId(uuId);
        instagramRepository.insertOrUpdateAccessToken(customerId, accessToken);     
    }

}
