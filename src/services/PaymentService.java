package services;

import entitites.Customer;
import entitites.Merchant;
import entitites.Payment;
import repository.CustomerRepo;
import repository.MerchantRepo;
import utils.ReadDataFromFile;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PaymentService {
    public Set<Payment> getPaymentsFromFile() throws IOException, SQLException {
        CustomerRepo customerRepo = new CustomerRepo();
        MerchantRepo merchantRepo = new MerchantRepo();
        Set<Payment> paymentsList = new HashSet<>();
        String path = "C:\\Users\\astar\\IdeaProjects\\CashManagement\\payments.dat";
        List<String> paymentsDataList = ReadDataFromFile.get(path);
        for (String str : paymentsDataList) {
            String[] tempArray = str.split(",");
            Timestamp date = getTimestamp(tempArray[0]);
            if (customerRepo.get(tempArray[1]) == null) {
                continue;
            }
            Customer customer = customerRepo.get(tempArray[1]);
            Merchant merchant = merchantRepo.get(tempArray[2]);
            String productName = tempArray[3];
            double sumPaid = Double.valueOf(tempArray[4]);
            double chargePaid = 0.00;

            paymentsList.add(new Payment(date, merchant, customer, productName, sumPaid, chargePaid));
        }
        return paymentsList;
    }

    public Timestamp getTimestamp(String str) {
        Timestamp date = Timestamp.valueOf(str);
        return date;
    }
}