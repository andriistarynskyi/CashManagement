package reportService;

import entity.Merchant;
import entity.Payment;
import entity.report.MerchantReport;
import service.MerchantService;
import service.PaymentService;

public class MerchantReportService {
    PaymentService paymentService = new PaymentService();
    MerchantService merchantService = new MerchantService();

    public double getTotalPaidToMerchantById(int id) {
        double totalSumPaid = 0;
        for (Payment p : paymentService.getAll()) {
            if (p.getMerchant().getId() == id) {
                totalSumPaid += p.getSumPaid();
            }
        }
        return totalSumPaid;
    }

    public MerchantReport getReport(int merchantId) {
        Merchant merchant = merchantService.getById(merchantId);
        return new MerchantReport(merchant,
                getTotalPaidToMerchantById(merchantId),
                merchant.getLastSent());
    }
}