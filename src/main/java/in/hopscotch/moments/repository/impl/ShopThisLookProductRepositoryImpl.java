package in.hopscotch.moments.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import in.hopscotch.moments.db.util.JDBCAccess;
import in.hopscotch.moments.entity.helper.ShopThisProductHelperEntity;
import in.hopscotch.moments.repository.ShopThisLookProductRepository;

@Repository
public class ShopThisLookProductRepositoryImpl implements ShopThisLookProductRepository {

    @Inject
    JDBCAccess jdbcAcces;

    private final String GET_SHOP_THIS_PRODUCT_INFO = "select pi.product_id, p.product_name, p.image_id, p.status , p.rack_status " + " sum(case when pi.is_presale = 0 then vi.available_quantity"
            + " when pi.is_presale = 0 then vi.presale_available_quantity end) as inv from products.product p" + " join products.productitem pi on p.id = pi.product_id"
            + " join products.productitemrack pir on pi.sku = pir.sku" + " join products.virtualinventory vi on vi.product_item_rack_id = pir.id" + " where p.id in ($PRODUCTID$) group by p.id";

    @Override
    public List<ShopThisProductHelperEntity> getShopThisProductHelperEntities(String productIds) {
        List<ShopThisProductHelperEntity> shopThisProductHelperEntities = new ArrayList<ShopThisProductHelperEntity>();
        GET_SHOP_THIS_PRODUCT_INFO.replace("$PRODUCTID$", productIds);
        shopThisProductHelperEntities = jdbcAcces.find(GET_SHOP_THIS_PRODUCT_INFO, new RowMapper<ShopThisProductHelperEntity>() {

            @Override
            public ShopThisProductHelperEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
                ShopThisProductHelperEntity shopThisProductHelperEntity = new ShopThisProductHelperEntity();
                shopThisProductHelperEntity.setProductId(rs.getInt("product_id"));
                shopThisProductHelperEntity.setProductName(rs.getString("product_name"));
                shopThisProductHelperEntity.setImageId(rs.getString("image_id"));
                shopThisProductHelperEntity.setInv(rs.getInt("inv"));
                shopThisProductHelperEntity.setStatus(rs.getString("status"));
                shopThisProductHelperEntity.setRackStatus(rs.getString("rack_status"));
                return shopThisProductHelperEntity;
            }
        });

        return shopThisProductHelperEntities;
    }

}
