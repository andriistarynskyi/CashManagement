package services;

import entitites.Customer;
import entitites.Merchant;
import entitites.Payment;
import repository.PaymentRepo;
import utils.DataFileReader;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PaymentService {
    MerchantService merchantService = new MerchantService();
    CustomerService customerService = new CustomerService();
    PaymentRepo paymentRepo = new PaymentRepo();

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
}