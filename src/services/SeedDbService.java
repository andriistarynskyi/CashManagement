package services;

import entitites.Customer;
import entitites.Merchant;
import entitites.Payment;
import repository.CustomerRepo;
import repository.MerchantRepo;
import repository.PaymentRepo;

import java.util.List;

public class SeedDbService {
    CustomerService customerService = new CustomerService();
    MerchantService merchantService = new MerchantService();
    PaymentService paymentService = new PaymentService();

    CustomerRepo customerRepo = new CustomerRepo();
    MerchantRepo merchantRepo = new MerchantRepo();
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

        List<Payment> paymentList = paymentService.getPaymentsFromFile();
        for (Payment p : paymentList) {
            paymentRepo.save(p);
        }
        System.out.println("Payments from file were added");
    }
}