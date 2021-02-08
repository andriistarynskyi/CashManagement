package entitites;

import java.util.Objects;

public class MerchantReport {
    private int merchantId;
    private String merchantName;
    private double totalSumPaid;
    private double lastSent;

    public MerchantReport() {
    }

    public MerchantReport(String merchantName,
                          double totalSumPaid,
                          double lastSent) {
        this.merchantName = merchantName;
        this.totalSumPaid = totalSumPaid;
        this.lastSent = lastSent;
    }

    public MerchantReport(int merchantId,
                          String merchantName,
                          double totalSumPaid,
                          double lastSent) {
        this.merchantId = merchantId;
        this.merchantName = merchantName;
        this.totalSumPaid = totalSumPaid;
        this.lastSent = lastSent;
    }

    public int getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public double getTotalSumPaid() {
        return totalSumPaid;
    }

    public void setTotalSumPaid(double totalSumPaid) {
        this.totalSumPaid = totalSumPaid;
    }

    public double getLastSent() {
        return lastSent;
    }

    public void setLastSent(double lastSent) {
        this.lastSent = lastSent;
    }

    @Override
    public String toString() {
        return "MerchantReport{" +
                "merchantId=" + merchantId +
                ", merchantName='" + merchantName + '\'' +
                ", totalSumPaid=" + totalSumPaid +
                ", lastSent=" + lastSent +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MerchantReport)) return false;
        MerchantReport that = (MerchantReport) o;
        return getMerchantId() == that.getMerchantId() && Double.compare(that.getTotalSumPaid(), getTotalSumPaid()) == 0 && Double.compare(that.getLastSent(), getLastSent()) == 0 && getMerchantName().equals(that.getMerchantName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMerchantId(), getMerchantName(), getTotalSumPaid(), getLastSent());
    }
}
