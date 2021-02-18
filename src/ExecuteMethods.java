import entity.Customer;
import entity.Merchant;
import entity.Payment;
import reportService.MerchantReportService;
import service.CustomerService;
import service.FileReaderService;
import service.MerchantService;
import service.PaymentService;

import java.sql.Timestamp;

public class ExecuteMethods {

    public void displayResults() {
        CustomerService customerService = new CustomerService();
        PaymentService paymentService = new PaymentService();
        MerchantService merchantService = new MerchantService();
        FileReaderService fileReaderService = new FileReaderService();
        MerchantReportService merchantReportService = new MerchantReportService();

//        seed data from files
        fileReaderService.saveDataFromFiles();

//        display all customers
        for (Customer c : customerService.getAll()) {
            System.out.println(c);
        }
        System.out.println("-----------------------------------");

//        display all merchants
        for (Merchant m : merchantService.getAll()) {
            System.out.println(m);
        }
        System.out.println("-----------------------------------");

//        display all payments
        for (Payment p : paymentService.getAll()) {
            System.out.println(p);
        }
        System.out.println("-----------------------------------");

//        Create a specific class and method that will show a total sum paid
//        for a merchant with a given id (argument).
        System.out.println(merchantReportService.getReport(2));
        System.out.println("-----------------------------------");

//       display merchants in alphabetical order
        for (Merchant m : merchantService.getSortedListInAlphabeticalOrder()) {
            System.out.println(m);
        }
        System.out.println("-----------------------------------");

//        add few more payments with updating corresponding columns at merchant table
        paymentService.save(new Payment(Timestamp.valueOf("2019-09-01 09:01:15"),
                merchantService.getById(2),
                customerService.getById(5),
                "Tree house",
                2508.08));
        paymentService.save(new Payment(Timestamp.valueOf("2017-09-01 09:01:15"),
                merchantService.getById(1),
                customerService.getById(7),
                "Carpet",
                3508.10));
//        Create a method that will send funds to a merchant if the needToSend>minSum.
//        This method should update the sent and lastSent columns in the Merchant table

//        Find the most active customer based on the number of order within the passed in time period
//        (ie week, month, quarter, year). The resulting Customer object should contain the list of all Payments made.
    }
}