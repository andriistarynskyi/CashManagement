package entity.report;

import entity.Merchant;

import java.time.LocalDate;
import java.util.Objects;

public class MerchantReport {
    private Merchant merchant;
    private double totalSumPaid;
    private LocalDate lastSent;

    public MerchantReport() {
    }

    public MerchantReport(Merchant merchant, double totalSumPaid, LocalDate lastSent) {
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

    public LocalDate getLastSent() {
        return lastSent;
    }

    public void setLastSent(LocalDate lastSent) {
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
        return Double.compare(that.getTotalSumPaid(), getTotalSumPaid()) == 0 && Objects.equals(getMerchant(), that.getMerchant()) && Objects.equals(getLastSent(), that.getLastSent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMerchant(), getTotalSumPaid(), getLastSent());
    }
}
