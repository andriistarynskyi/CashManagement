package service;

import entity.Customer;
import entity.Merchant;
import entity.Payment;

import java.util.List;

public class FileReaderService {
    CustomerService customerService = new CustomerService();
    MerchantService merchantService = new MerchantService();
    PaymentService paymentService = new PaymentService();

    public void saveDataFromFiles() {

        List<Customer> customerList = customerService.getCustomersFromFile();
        for (Customer c : customerList) {
            customerService.save(c);
        }
        System.out.println("Customers from file were added to DB");

        List<Merchant> merchantList = merchantService.getMerchantsFromFile();
        for (Merchant m : merchantList) {
            merchantService.save(m);
        }
        System.out.println("Merchants from file were added to DB");

        List<Payment> paymentsFromFile = paymentService.getPaymentsFromFile();
        for (Payment p : paymentsFromFile) {
            paymentService.save(p);
        }
        System.out.println("Payments from file were added to DB");
    }
}