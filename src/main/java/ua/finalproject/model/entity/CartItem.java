package ua.finalproject.model.entity;

public class CartItem {
    private int id;
    private Product product;
    private int amount;

    public CartItem() {
    }

    public CartItem(Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    public CartItem(int id, Product product, int amount) {
        this.id = id;
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

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", product=" + product +
                ", quantity=" + amount +
                '}';
    }
}
