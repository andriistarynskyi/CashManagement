package entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Merchant {
    private int id;
    private String name;
    private String bankName;
    private String swift;
    private String account;
    private double charge;
    private int period;
    private double minSum;
    private double needToSend;
    private double sentAmount;
    private LocalDate lastSent;
    private List<Payment> paymentsList;

    public Merchant() {
    }

    public Merchant(int id, String name, String bankName,
                    String swift, String account, double charge,
                    int period, double minSum, double needToSend,
                    double sentAmount, LocalDate lastSent) {
        this.id = id;
        this.name = name;
        this.bankName = bankName;
        this.swift = swift;
        this.account = account;
        this.charge = charge;
        this.period = period;
        this.minSum = minSum;
        this.needToSend = needToSend;
        this.sentAmount = sentAmount;
        this.lastSent = lastSent;
    }

    public List<Payment> getPaymentsList() {
        return paymentsList;
    }

    public void setPaymentsList(List<Payment> paymentsList) {
        this.paymentsList = paymentsList;
    }

    public Merchant(String name, String bankName,
                    String swift, String account,
                    double charge, int period,
                    double minSum, double needToSend,
                    double sentAmount, LocalDate lastSent) {
        this.name = name;
        this.bankName = bankName;
        this.swift = swift;
        this.account = account;
        this.charge = charge;
        this.period = period;
        this.minSum = minSum;
        this.needToSend = needToSend;
        this.sentAmount = sentAmount;
        this.lastSent = lastSent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getSwift() {
        return swift;
    }

    public void setSwift(String swift) {
        this.swift = swift;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public double getCharge() {
        return charge;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public double getMinSum() {
        return minSum;
    }

    public void setMinSum(double minSum) {
        this.minSum = minSum;
    }

    public double getNeedToSend() {
        return needToSend;
    }

    public void setNeedToSend(double needToSend) {
        this.needToSend = needToSend;
    }

    public double getSentAmount() {
        return sentAmount;
    }

    public void setSentAmount(double sentAmount) {
        this.sentAmount = sentAmount;
    }

    public LocalDate getLastSent() {
        return lastSent;
    }

    public void setLastSent(LocalDate lastSent) {
        this.lastSent = lastSent;
    }

    @Override
    public String toString() {
        return "Merchant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bankName='" + bankName + '\'' +
                ", swift='" + swift + '\'' +
                ", account='" + account + '\'' +
                ", charge=" + charge +
                ", period=" + period +
                ", minSum=" + minSum +
                ", needToSend=" + needToSend +
                ", sentAmount=" + sentAmount +
                ", lastSent=" + lastSent +
                ", paymentsList=" + paymentsList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Merchant)) return false;
        Merchant merchant = (Merchant) o;
        return getId() == merchant.getId() && Double.compare(merchant.getCharge(), getCharge()) == 0 && getPeriod() == merchant.getPeriod() && Double.compare(merchant.getMinSum(), getMinSum()) == 0 && Double.compare(merchant.getNeedToSend(), getNeedToSend()) == 0 && Double.compare(merchant.getSentAmount(), getSentAmount()) == 0 && getName().equals(merchant.getName()) && getBankName().equals(merchant.getBankName()) && getSwift().equals(merchant.getSwift()) && getAccount().equals(merchant.getAccount()) && getLastSent().equals(merchant.getLastSent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getBankName(), getSwift(), getAccount(), getCharge(), getPeriod(), getMinSum(), getNeedToSend(), getSentAmount(), getLastSent());
    }
}