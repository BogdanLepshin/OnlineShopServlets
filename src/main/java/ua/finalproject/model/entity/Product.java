package ua.finalproject.model.entity;

import java.util.Objects;

public class Product {
    private int id;
    private String name;
    private String image;
    private Category category;
    private Brand brand;
    private String descriptionRu;
    private String descriptionEn;
    private int price;
    private boolean enabled;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public void setDescriptionRu(String descriptionRu) {
        this.descriptionRu = descriptionRu;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public Category getCategory() {
        return category;
    }

    public Brand getBrand() {
        return brand;
    }

    public String getDescriptionRu() {
        return descriptionRu;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }


    public int getPrice() {
        return price;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public static class Builder {
        Product product;

        public Builder() {
            product = new Product();
        }

        public Builder setId(int id) {
            product.id = id;
            return this;
        }

        public Builder setName(String name) {
            product.name = name;
            return this;
        }

        public Builder setImage(String image) {
            product.image = image;
            return this;
        }

        public Builder setCategory(Category categoryId) {
            product.category = categoryId;
            return this;
        }

        public Builder setBrand(Brand brandId) {
            product.brand = brandId;
            return this;
        }

        public Builder setDescriptionRu(String descriptionRu) {
            product.descriptionRu = descriptionRu;
            return this;
        }

        public Builder setDescriptionEn(String descriptionEn) {
            product.descriptionEn = descriptionEn;
            return this;
        }

        public Builder setPrice(int price) {
            product.price = price;
            return this;
        }

        public Builder setEnabled(boolean enabled) {
            product.enabled = enabled;
            return this;
        }

        public Product build() {
            return product;
        }

    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", category=" + category +
                ", brand='" + brand + '\'' +
                ", descriptionRu='" + descriptionRu + '\'' +
                ", descriptionEn='" + descriptionEn + '\'' +
                ", price=" + price +
                ", enabled=" + enabled +
                '}';
    }
}
