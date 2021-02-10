package repository;

import entitites.Customer;
import entitites.Merchant;
import entitites.Payment;
import services.CustomerService;
import services.MerchantService;
import services.PaymentService;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class SeedDb {
    public void saveAll() {
        CustomerService customerService = new CustomerService();
        CustomerRepo customerRepo = new CustomerRepo();
        Set<Customer> customersList = customerService.getCustomersFromFile();
        try {
            customerRepo.saveAll(customersList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        MerchantService merchantService = new MerchantService();
        List<Merchant> merchantsList = merchantService.getMerchantFromFile();
        MerchantRepo merchantRepo = new MerchantRepo();
        merchantRepo.saveAll(merchantsList);

        PaymentService paymentService = new PaymentService();
        Set<Payment> paymentsList = paymentService.getPaymentsFromFile();
        PaymentRepo paymentRepo = new PaymentRepo();
        paymentRepo.saveAll(paymentsList);
    }
}