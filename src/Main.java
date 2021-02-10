import entitites.Customer;
import entitites.Merchant;
import entitites.Payment;
import entitites.report.MerchantReport;
import repository.*;
import repository.report.MerchantReportRepo;
import services.CustomerService;
import services.MerchantService;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {

        MerchantReportRepo merchantReportRepo = new MerchantReportRepo();
        MerchantRepo merchantRepo = new MerchantRepo();
        PaymentRepo paymentRepo = new PaymentRepo();
        CustomerRepo customerRepo = new CustomerRepo();
        MerchantService merchantService = new MerchantService();
        CustomerService customerService = new CustomerService();

//        seed data from files
        SeedDb seeDb = new SeedDb();
        seeDb.saveAll();

//        create custom vendor report
        List<MerchantReport> merchantReports = merchantReportRepo.getMerChantReport();
        for (MerchantReport m : merchantReports) {
            System.out.println(m);
        }

//        get sorted list of merchants in alphabetical order
        List<Merchant> merchantList = merchantRepo.getAllInAlphabeticalOrder();
        for (Merchant merchant : merchantList) {
            System.out.println(merchant);
        }

//        add couple of new payments
        Payment paymentOne = new Payment(new Timestamp(System.currentTimeMillis()),
                merchantRepo.getById(2), customerRepo.getById(4),
                "Laptop", 2867.00, 0.00);
        paymentRepo.save(paymentOne);

        Payment paymentTwo = new Payment(java.sql.Timestamp.valueOf("2021-01-23 10:10:10.0"),
                merchantRepo.getById(1), customerRepo.getById(3),
                "Plastic table", 25000.00, 0.00);
        paymentRepo.save(paymentTwo);

//        update vendor payments
        merchantService.sentPaymentToMerchants();

//        get customer who spend the most within certain period of time
        LocalDate startDate = LocalDate.of(2018, 10, 20);
        LocalDate endDate = LocalDate.of(2020, 12, 31);
        Customer mostActiveCustomer = customerService.getMostActiveCustomer(startDate, endDate);
        System.out.println(mostActiveCustomer);
    }
}