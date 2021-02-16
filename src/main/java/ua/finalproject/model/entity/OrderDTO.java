package ua.finalproject.model.entity;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class OrderDTO {
    private int id;
    private int userId;
    private LocalDateTime creationDate;
    private LocalDateTime completionDate;
    private Payment payment;
    private Delivery delivery;
    private OrderStatus status;
    private int totalPrice;
    private List<OrderDetails> orderDetailsList;

    public OrderDTO() {
        orderDetailsList = new ArrayList<>();
    }

    public String formatCreationDateRu() {
        return creationDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss", new Locale("ru", "RU")));
    }

    public String formatCompletionDateRu() {
        if (completionDate == null) {
            return null;
        }
        return completionDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss", new Locale("ru", "RU")));
    }

    public String formatCreationDateEn() {
        return creationDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss", new Locale("en", "US")));
    }

    public String formatCompletionDateEn() {
        if (completionDate == null) {
            return null;
        }
        return completionDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss", new Locale("en", "US")));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDateTime completionDate) {
        this.completionDate = completionDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<OrderDetails> getOrderDetailsList() {
        return orderDetailsList;
    }

    public void setOrderDetailsList(List<OrderDetails> orderDetailsList) {
        this.orderDetailsList = orderDetailsList;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", userId=" + userId +
                ", creationDate=" + creationDate +
                ", completionDate=" + completionDate +
                ", payment=" + payment +
                ", delivery=" + delivery +
                ", status=" + status +
                ", totalPrice=" + totalPrice +
                ", orderDetailsList=" + orderDetailsList +
                '}';
    }
}
