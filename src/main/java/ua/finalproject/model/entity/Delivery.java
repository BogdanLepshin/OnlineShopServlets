package ua.finalproject.model.entity;

public class Delivery {
    private int id;
    private String typeRu;
    private String typeEn;

    public Delivery() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeRu() {
        return typeRu;
    }

    public void setTypeRu(String typeRu) {
        this.typeRu = typeRu;
    }

    public String getTypeEn() {
        return typeEn;
    }

    public void setTypeEn(String typeEn) {
        this.typeEn = typeEn;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "id=" + id +
                ", typeRu='" + typeRu + '\'' +
                ", typeEn='" + typeEn + '\'' +
                '}';
    }
}
