package ua.finalproject.model.entity;

public class Payment {
    private int id;
    private String paymentTypeRu;
    private String paymentTypeEn;

    public Payment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPaymentTypeRu() {
        return paymentTypeRu;
    }

    public void setPaymentTypeRu(String paymentTypeRu) {
        this.paymentTypeRu = paymentTypeRu;
    }

    public String getPaymentTypeEn() {
        return paymentTypeEn;
    }

    public void setPaymentTypeEn(String paymentTypeEn) {
        this.paymentTypeEn = paymentTypeEn;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", paymentTypeRu='" + paymentTypeRu + '\'' +
                ", paymentTypeEn='" + paymentTypeEn + '\'' +
                '}';
    }
}
