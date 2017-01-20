package in.hopscotch.moments.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import org.springframework.jdbc.core.RowMapper;
import in.hopscotch.moments.db.util.JDBCAccess;
import in.hopscotch.moments.entity.InstagramAccessToken;
import in.hopscotch.moments.repository.InstagramRepository;

@Repository
public class InstagramRepositoryImpl extends AbstractRepository<InstagramAccessToken> implements InstagramRepository {
    
    private static final String GET_CUSTOMERID_FROM_UUID = "select id as customer_id from customers.customer where uu_id = ?";
    private static final String INSERT_OR_UPDATE_INSTAGRAM_ACCESS_TOKEN = "insert into hsmoments.instagram_access_token(customer_id, access_token, created_date, updated_date)"
        + " values (?, ?, ?, ?) on duplicate key update access_token = ?, updated_date = ?";
    
    @Inject
    JDBCAccess jdbcAccess;
    
    @Override
    public Integer getCustomerIdFromUUId(String uuId) {
        return jdbcAccess.findOne(GET_CUSTOMERID_FROM_UUID, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet resultSet,  int rowNum) throws SQLException {
                return resultSet.getInt("customer_id");
            }
        }, uuId);
    }
    
    @Override
    public void insertOrUpdateAccessToken(Integer customerId, String accessToken) {
        jdbcAccess.update(INSERT_OR_UPDATE_INSTAGRAM_ACCESS_TOKEN, customerId, accessToken, LocalDateTime.now(), LocalDateTime.now(),
            accessToken, LocalDateTime.now());
    }

}
