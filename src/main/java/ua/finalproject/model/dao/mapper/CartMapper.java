package ua.finalproject.model.dao.mapper;

import ua.finalproject.model.entity.CartDTO;
import ua.finalproject.model.entity.CartItem;
import ua.finalproject.model.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class CartMapper implements ObjectMapper<CartDTO>{

    private final Logger LOGGER = Logger.getLogger(CartMapper.class.getName());

    @Override
    public CartDTO extractFromResultSet(ResultSet rs) throws SQLException {
        CartDTO cart = null;
        List<CartItem> products = null;
        while(rs.next()) {
            if (cart == null) {
                cart = new CartDTO();
                products = cart.getItems();
                cart.setId(rs.getInt("c.id"));
                cart.setUserId(rs.getInt("c.user_id"));

            }
            cart.setTotalPrice(cart.getTotalPrice()+rs.getInt("c.total_price"));
            Product product = new Product.Builder()
                    .setId(rs.getInt("p.id"))
                    .setName(rs.getString("p.name"))
                    .setImage(rs.getString("p.image"))
                    .setPrice(rs.getInt("p.price"))
                    .build();
            products.add(new CartItem(rs.getInt("i.id"),product, rs.getInt("i.amount")));
            LOGGER.info("cart: " + cart.toString());
        }

        return cart;
    }
}
