package entitites.report;

import entitites.Merchant;

import java.util.Objects;

public class MerchantReport {
    private Merchant merchant;
    private double totalSumPaid;
    private double lastSent;

    public MerchantReport() {
    }

    public MerchantReport(Merchant merchant, double totalSumPaid, double lastSent) {
        this.merchant = merchant;
        this.totalSumPaid = totalSumPaid;
        this.lastSent = lastSent;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
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
                "merchant=" + merchant +
                ", totalSumPaid=" + totalSumPaid +
                ", lastSent=" + lastSent +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MerchantReport)) return false;
        MerchantReport that = (MerchantReport) o;
        return Double.compare(that.getTotalSumPaid(), getTotalSumPaid()) == 0 && Double.compare(that.getLastSent(), getLastSent()) == 0 && getMerchant().equals(that.getMerchant());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMerchant(), getTotalSumPaid(), getLastSent());
    }
}
