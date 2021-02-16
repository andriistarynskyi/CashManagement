import entitites.Customer;
import entitites.Payment;
import repository.CustomerRepo;
import repository.MerchantRepo;
import repository.PaymentRepo;
import repository.report.MerchantReportRepo;
import services.CustomerService;
import services.MerchantService;
import services.PaymentService;
import services.SeedDbService;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {

        MerchantReportRepo merchantReportRepo = new MerchantReportRepo();
        MerchantRepo merchantRepo = new MerchantRepo();
        PaymentRepo paymentRepo = new PaymentRepo();
        CustomerRepo customerRepo = new CustomerRepo();

        MerchantService merchantService = new MerchantService();
        CustomerService customerService = new CustomerService();
        PaymentService paymentService = new PaymentService();
        CustomerRepo customerRepo1 = new CustomerRepo();
        SeedDbService seedDbService = new SeedDbService();

        seedDbService.saveDataFromFiles();


//        List<Payment> paymentList = paymentService.getPaymentsFromFile();
//        for(Payment p : paymentList) {
//            System.out.println(p);
//        }

//        List<Payment> paymentsList =  paymentService.getPaymentsFromFile();
//        for(Payment p : paymentsList) {
//            System.out.println(p);
//        }


//        create custom vendor report
//        List<MerchantReport> merchantReports = merchantReportRepo.getMerChantReport();
//        for (MerchantReport m : merchantReports) {
//            System.out.println(m);
//        }

////        get sorted list of merchants in alphabetical order
//        List<Merchant> merchantList = merchantRepo.getAllInAlphabeticalOrder();
//        for (Merchant merchant : merchantList) {
//            System.out.println(merchant);
//        }
//
////        add couple of new payments
//        Payment paymentOne = new Payment(new Timestamp(System.currentTimeMillis()),
//                merchantRepo.getById(2), customerRepo.getById(4),
//                "Laptop", 2867.00, 0.00);
//        paymentRepo.save(paymentOne);
//
//        Payment paymentTwo = new Payment(java.sql.Timestamp.valueOf("2021-01-23 10:10:10.0"),
//                merchantRepo.getById(1), customerRepo.getById(3),
//                "Plastic table", 25000.00, 0.00);
//        paymentRepo.save(paymentTwo);
//
////        update vendor payments
//        merchantService.sentPaymentToMerchants();
//
////        get customer who spend the most within certain period of time
//        LocalDate startDate = LocalDate.of(2018, 10, 20);
//        LocalDate endDate = LocalDate.of(2020, 12, 31);
    }
}