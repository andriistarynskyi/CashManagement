package service;

import entity.Customer;
import entity.Merchant;
import entity.Payment;
import repository.CustomerRepo;
import repository.MerchantRepo;
import repository.PaymentRepo;
import utils.DataFileReader;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PaymentService {
    private PaymentRepo paymentRepo;
    private MerchantRepo merchantRepo;
    private CustomerRepo customerRepo;
    private MerchantService merchantService;
    private CustomerService customerService;

    public PaymentService() {
        this.paymentRepo = new PaymentRepo();
        this.merchantRepo = new MerchantRepo();
        this.customerRepo = new CustomerRepo();

        this.paymentRepo.setMerchantRepo(merchantRepo);
        this.paymentRepo.setCustomerRepo(customerRepo);
        this.merchantService = new MerchantService();
    }

    public final double commissionRate = 0.02;

    public List<Payment> getPaymentsFromFile() {
        List<Payment> paymentsList = new ArrayList<>();
        String path = "C:\\Users\\astar\\IdeaProjects\\CashManagement\\payments.dat";
        List<String> paymentsDataList = DataFileReader.getDataFromFile(path);
        for (String str : paymentsDataList) {
            String[] tempArray = str.split(",");
            Timestamp date = parseDate(tempArray[0]);
            Customer customer = customerService.getByName(tempArray[1]);
            if (customer == null) {
                continue;
            }
            Merchant merchant = merchantService.getByName(tempArray[2]);
            if (merchant == null) {
                continue;
            }
            String productName = tempArray[3];
            double sumPaid = Double.valueOf(tempArray[4]);

            paymentsList.add(new Payment(date, merchant, customer, productName, sumPaid));
        }
        return paymentsList;
    }

    public Timestamp parseDate(String str) {
        Timestamp date = Timestamp.valueOf(str);
        return date;
    }

    public List<Payment> getAll() {
        return paymentRepo.getAll();
    }

    public List<Payment> getPaymentsByCustomerId(Customer customer) {
        return paymentRepo.getByCustomer(customer);
    }
}