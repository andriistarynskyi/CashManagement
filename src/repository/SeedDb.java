package repository;

import entitites.Customer;
import entitites.Merchant;
import entitites.Payment;
import services.CustomerService;
import services.MerchantService;
import services.PaymentService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class SeedDb {
    public void add() throws IOException, SQLException {
        CustomerService customerService = new CustomerService();
        CustomerRepo customerRepo = new CustomerRepo();
        Set<Customer> customersList = customerService.getCustomersFromFile();
        customerRepo.add(customersList);

        MerchantService merchantService = new MerchantService();
        List<Merchant> merchantsList = merchantService.getMerchantFromFile();
        MerchantRepo merchantRepo = new MerchantRepo();
        merchantRepo.add(merchantsList);

        PaymentService paymentService = new PaymentService();
        Set<Payment> paymentsList = paymentService.getPaymentsFromFile();
        PaymentRepo paymentRepo = new PaymentRepo();
        paymentRepo.add(paymentsList);
    }
}
