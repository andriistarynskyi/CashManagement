package entity;

import java.sql.Timestamp;
import java.util.Objects;

public class Payment {
    private int id;
    private Timestamp date;
    private Merchant merchant;
    private Customer customer;
    private String productName;
    private double sumPaid;
    private double chargePaid;

    public Payment() {
    }

    public Payment(int id, Timestamp date, Merchant merchant,
                   Customer customer, String productName,
                   double sumPaid) {
        this.id = id;
        this.date = date;
        this.merchant = merchant;
        this.customer = customer;
        this.productName = productName;
        this.sumPaid = sumPaid;
    }

    public Payment(Timestamp date, Merchant merchant, Customer customer,
                   String productName, double sumPaid) {
        this.date = date;
        this.merchant = merchant;
        this.customer = customer;
        this.productName = productName;
        this.sumPaid = sumPaid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getSumPaid() {
        return sumPaid;
    }

    public void setSumPaid(double sumPaid) {
        this.sumPaid = sumPaid;
    }

    public double getChargePaid() {
        return chargePaid;
    }

    public void setChargePaid(double chargePaid) {
        this.chargePaid = chargePaid;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", date=" + date +
                ", merchant=" + merchant +
                ", customer=" + customer +
                ", productName='" + productName + '\'' +
                ", sumPaid=" + sumPaid +
                ", chargePaid=" + chargePaid +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment)) return false;
        Payment payment = (Payment) o;
        return getId() == payment.getId() && Double.compare(payment.getSumPaid(), getSumPaid()) == 0 && Double.compare(payment.getChargePaid(), getChargePaid()) == 0 && getDate().equals(payment.getDate()) && getMerchant().equals(payment.getMerchant()) && getCustomer().equals(payment.getCustomer()) && getProductName().equals(payment.getProductName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDate(), getMerchant(), getCustomer(), getProductName(), getSumPaid(), getChargePaid());
    }
}