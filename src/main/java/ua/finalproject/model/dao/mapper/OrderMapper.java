package ua.finalproject.model.dao.mapper;

import ua.finalproject.model.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class OrderMapper implements ObjectMapper<OrderDTO> {

    private final Logger LOGGER = Logger.getLogger(OrderMapper.class.getName());

    @Override
    public OrderDTO extractFromResultSet(ResultSet rs) throws SQLException {
        return initOrder(rs);
    }

    public OrderDTO extractOrderWithOrderDetailsFromResultSet(ResultSet rs) throws SQLException {
        OrderDTO orderDTO = null;

        while (rs.next()) {
            if (isFirstInitialization(orderDTO)) {
                orderDTO = initOrder(rs);
            }
            addOrderDetails(rs, orderDTO.getOrderDetailsList());
        }
        return orderDTO;
    }


    public List<OrderDTO> extractOrderListFromRs(ResultSet rs) throws SQLException {
        List<OrderDTO> orders = new ArrayList<>();
        OrderDTO orderDTO = null;

        while (rs.next()) {
            if (isFirstInitialization(orderDTO)) {
                orderDTO = initOrder(rs);
            }

            if (isNextOrder(rs, orderDTO)) {
                orders.add(orderDTO);
                orderDTO = initOrder(rs);
            }
            addOrderDetails(rs, orderDTO.getOrderDetailsList());
        }

        if (hasOrder(orderDTO)) {
            LOGGER.info("order: " + orderDTO.toString());
            orders.add(orderDTO);
        }
        return orders;
    }

    public OrderStatus extractStatusFromRs(ResultSet rs) throws SQLException {
        OrderStatus orderStatus = new OrderStatus();

        orderStatus.setId(rs.getInt("id"));
        orderStatus.setNameRu(rs.getString("name_ru"));
        orderStatus.setNameEn(rs.getString("name_en"));

        return orderStatus;
    }

    private boolean hasOrder(OrderDTO orderDTO) {
        return orderDTO != null;
    }


    private boolean isFirstInitialization(OrderDTO orderDTO) {
        return orderDTO == null;
    }

    private boolean isNextOrder(ResultSet rs, OrderDTO orderDTO) throws SQLException {
        return orderDTO.getId() != rs.getInt("o.id");
    }

    private OrderDTO initOrder(ResultSet rs) throws SQLException {
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setId(rs.getInt("o.id"));
        orderDTO.setUserId(rs.getInt("o.user_id"));
        orderDTO.setCreationDate(rs.getTimestamp("creation_date").toLocalDateTime());

        Timestamp timestamp = rs.getTimestamp("completion_date");
        orderDTO.setCompletionDate(timestamp == null ? null : timestamp.toLocalDateTime());

        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setId(rs.getInt("os.id"));
        orderStatus.setNameRu(rs.getString("os.name_ru"));
        orderStatus.setNameEn(rs.getString("os.name_en"));
        orderDTO.setStatus(orderStatus);

        Payment payment = new Payment();
        payment.setId(rs.getInt("pt.id"));
        payment.setPaymentTypeRu(rs.getString("pt.payment_type_ru"));
        payment.setPaymentTypeEn(rs.getString("pt.payment_type_en"));
        orderDTO.setPayment(payment);

        Delivery delivery = new Delivery();
        delivery.setId(rs.getInt("d.id"));
        delivery.setTypeRu(rs.getString("d.type_ru"));
        delivery.setTypeEn(rs.getString("d.type_en"));
        orderDTO.setDelivery(delivery);

        orderDTO.setTotalPrice(rs.getInt("o.total_price"));

        return orderDTO;
    }

    private void addOrderDetails(ResultSet rs, List<OrderDetails> orderDetailsList) throws SQLException {
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

        orderDetailsList.add(orderDetails);
    }
}
