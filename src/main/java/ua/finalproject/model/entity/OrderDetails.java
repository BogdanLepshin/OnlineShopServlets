package ua.finalproject.model.entity;

public class OrderDetails {
    private int id;
    private int orderId;
    private Product product;
    private int amount;

    public OrderDetails() {
    }

    public OrderDetails(int orderId, Product product, int amount) {
        this.orderId = orderId;
        this.product = product;
        this.amount = amount;
    }

    public OrderDetails(int id, int orderId, Product product, int amount) {
        this.id = id;
        this.orderId = orderId;
        this.product = product;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", product=" + product +
                ", amount=" + amount +
                '}';
    }
}
