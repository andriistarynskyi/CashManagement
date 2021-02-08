import entitites.Merchant;
import entitites.MerchantReport;
import repository.MerchantRepo;
import repository.MerchantReportRepo;
import repository.PaymentRepo;
import repository.SeedDb;
import services.MerchantService;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {

//        seed data from files
        SeedDb seeDb = new SeedDb();
        seeDb.add();

//        create custom vendor report
        MerchantReportRepo merchantReportRepo = new MerchantReportRepo();
        List<MerchantReport> merchantReports = merchantReportRepo.getMerChantReport();
        for (MerchantReport m : merchantReports) {
            System.out.println(m);
        }

//        get sorted list of merchants in alphabetical order
        MerchantRepo merchantRepo = new MerchantRepo();
        List<Merchant> merchantList = merchantRepo.getSortedList();
        for (Merchant merchant : merchantList) {
            System.out.println(merchant);
        }

//        add couple of new payments
        PaymentRepo paymentRepo = new PaymentRepo();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        paymentRepo.addPayment(timestamp, 2, 4, "Laptop", 2867.00, 0.00);
        Timestamp timestamp2 = java.sql.Timestamp.valueOf("2021-01-23 10:10:10.0");
        paymentRepo.addPayment(timestamp2, 1, 5, "Plastic table", 1250.00, 0.00);

//        update vendor payments
        MerchantService merchantService = new MerchantService();
        merchantService.sendPaymentToMerchant("Amazon");
    }
}