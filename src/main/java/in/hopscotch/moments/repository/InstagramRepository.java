package in.hopscotch.moments.repository;

public interface InstagramRepository {
    
    Integer getCustomerIdFromUUId(String uuId);
    
    void insertOrUpdateAccessToken(Integer customerId, String accessToken);
    
}
