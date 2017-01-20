package in.hopscotch.moments.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import in.hopscotch.moments.api.request.ImageFileRequest;
import in.hopscotch.moments.api.response.ChildInfo;
import in.hopscotch.moments.api.response.DeliveredProducts;
import in.hopscotch.moments.api.response.ImageResponse;
import in.hopscotch.moments.constant.NamedQueryConstant;
import in.hopscotch.moments.db.util.JDBCAccess;
import in.hopscotch.moments.entity.HSMomentsData;
import in.hopscotch.moments.helper.UploadStrategy;
import in.hopscotch.moments.repository.HSMomentsRepository;
import in.hopscotch.moments.util.S3Client;

@Repository
public class HSMomentsRepositoryImpl extends AbstractRepository<HSMomentsData> implements HSMomentsRepository {

    @Inject
    JDBCAccess jdbcAccess;
    
    @Inject
    S3Client s3Client;
    
    @Value("${CommonUploadsTStrategy}")
    String uploadStrategy;

    public static final String DELIVERED_PRODUCTS = "select pi.sku as sku, p.id as id, p.product_name as name, p.image_id as image from orders.order o "
        + " inner join orders.orderitem oi on oi.order_id=o.id " + " inner join products.productitem pi on pi.sku=oi.sku " + " inner join products.product p on p.id=pi.product_id "
        + " where o.customer_id=? and o.order_status_id=6 group by sku order by o.id desc";

    public static final String CHILD_INFO = "SELECT id, name, customer_id FROM customers.youngestchildageandgendercollect where customer_id=?";

    @Override
    public List<HSMomentsData> getHSMomentsData(boolean newest, int pageNo, int pageSize) {
        List<HSMomentsData> hsMomentsResponse = findByPaginationUsingNamedQuery(newest ? NamedQueryConstant.HSMOMENTSDATA_NEWEST : NamedQueryConstant.HSMOMENTSDATA_POPULAR, pageNo - 1, pageSize);
        return hsMomentsResponse;
    }

    @Override
    public List<DeliveredProducts> getDeliveredProductInfo(Integer customerId) {
        List<DeliveredProducts> deliveredproducts = jdbcAccess.find(DELIVERED_PRODUCTS, new RowMapper<DeliveredProducts>() {
            @Override
            public DeliveredProducts mapRow(ResultSet rs, int rowNum) throws SQLException {
                DeliveredProducts dp = new DeliveredProducts();
                dp.setSku(rs.getString("sku"));
                dp.setProductId(rs.getInt("id"));
                dp.setProductName(rs.getString("name"));
                dp.setImageUrl(rs.getString("image"));
                return dp;
            }
        }, customerId);
        return deliveredproducts;
    }

    @Override
    public List<ChildInfo> getChildInfo(Integer customerId) {
        List<ChildInfo> childInfos = jdbcAccess.find(CHILD_INFO, new RowMapper<ChildInfo>() {
            @Override
            public ChildInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                ChildInfo ci = new ChildInfo();
                ci.setId(rs.getInt("id"));
                ci.setName(rs.getString("name"));
                ci.setCustomerId(rs.getInt("customer_id"));
                return ci;
            }
        }, customerId);
        return childInfos;
    }

    public void incrementLike(Long momentsPhotoId) {
        executeUpdateUsingNamedQuery(NamedQueryConstant.HSMOMENTSDATA_INCREMENT_LIKES);
    }
    
    public ImageResponse uploadImageFile(ImageFileRequest imageFileRequest) throws Exception {
        String className = uploadStrategy;
        UploadStrategy uploadStrategy = (UploadStrategy) Class.forName(className).newInstance();
        uploadStrategy.setJpaAccess(jpaAccess);
        uploadStrategy.setS3Client(s3Client);
        return uploadStrategy.uploadImageFile(imageFileRequest, new Date());
    }

}
