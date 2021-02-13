package ua.finalproject.model.entity;

import java.util.*;

public class CartDTO {
    private int id;
    private int userId;
    private List<CartItem> cartItems;
    private int totalPrice;

    public CartDTO() {
        cartItems = new ArrayList<>();
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<CartItem> getItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
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

    @Override
    public String toString() {
        return "CartDTO{" +
                "id=" + id +
                ", userId=" + userId +
                ", cartItems=" + cartItems +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
