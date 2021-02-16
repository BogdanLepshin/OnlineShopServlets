package ua.finalproject.model.dao.mapper;

import ua.finalproject.model.entity.OrderDetails;
import ua.finalproject.model.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDetailsMapper implements ObjectMapper<OrderDetails> {
    @Override
    public OrderDetails extractFromResultSet(ResultSet rs) throws SQLException {
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setId(rs.getInt("od.id"));
        orderDetails.setOrderId(rs.getInt("od.order_id"));
        orderDetails.setAmount(rs.getInt("od.amount"));
        orderDetails.setProduct(new Product.Builder()
                .setId(rs.getInt("p.id"))
                .setImage(rs.getString("p.image"))
                .setName(rs.getString("p.name"))
                .setPrice(rs.getInt("p.price"))
                .build());
        return orderDetails;
    }
}
