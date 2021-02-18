package service;

import entity.Customer;
import entity.Merchant;
import entity.Payment;
import repository.CustomerRepo;
import repository.MerchantRepo;
import repository.PaymentRepo;

import java.util.List;

public class ExternalFileService {
    CustomerService customerService = new CustomerService();
    MerchantService merchantService = new MerchantService();
    PaymentService paymentService = new PaymentService();

    CustomerRepo customerRepo;
    MerchantRepo merchantRepo;
    PaymentRepo paymentRepo = new PaymentRepo();

    public void saveDataFromFiles() {

        List<Customer> customerList = customerService.getCustomersFromFile();
        for (Customer c : customerList) {
            customerRepo.save(c);
        }
        System.out.println("Customers from file were added");

        List<Merchant> merchantList = merchantService.getMerchantsFromFile();
        for (Merchant m : merchantList) {
            merchantRepo.save(m);
        }
        System.out.println("Merchants from file were added");

        List<Payment> paymentsFromFile = paymentService.getPaymentsFromFile();
        for (Payment p : paymentsFromFile) {
            paymentRepo.save(p);
        }
        System.out.println("Payments from file were added");
    }
}