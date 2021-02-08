package services;

import entitites.Merchant;
import repository.MerchantRepo;
import repository.PaymentRepo;
import utils.ReadDataFromFile;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MerchantService {
    MerchantRepo merchantRepo = new MerchantRepo();
    PaymentRepo paymentRepo = new PaymentRepo();

    public List<Merchant> getMerchantFromFile() {
        String path = "C:\\Users\\astar\\IdeaProjects\\CashManagement\\merchants.dat";
        List<Merchant> merchantsList = new ArrayList<>();
        List<String> merchantsData = ReadDataFromFile.get(path);
        for (String str : merchantsData) {
            String[] tempArray = str.split(",");
            String name = tempArray[0];
            String bankName = tempArray[1];
            String swift = tempArray[2];
            String account = tempArray[3];
            double charge = Double.valueOf(tempArray[4]);
            int period = Integer.valueOf(tempArray[5]);
            double minSum = Double.valueOf(tempArray[6]);
            double needToSent = Double.valueOf(tempArray[7]);
            double sentAmount = Double.valueOf(tempArray[8]);
            LocalDate lastSent;
            if (getLastSentDate(tempArray[9]) == null) {
                lastSent = null;
            } else {
                lastSent = getLastSentDate(tempArray[9]);
            }
            merchantsList.add(new Merchant(name, bankName, swift,
                    account, charge, period, minSum, needToSent, sentAmount, lastSent));
        }
        return merchantsList;
    }

    public LocalDate getLastSentDate(String str) {
        String receivedDateString = str.toUpperCase();
        LocalDate date = null;
        if (!receivedDateString.equals("NULL")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/M/dd");
            date = LocalDate.parse(str, formatter);
        } else {
            date = null;
        }
        return date;
    }

    public void sendPaymentToMerchant(String merchantName) throws IOException, SQLException {
        Merchant merchant = merchantRepo.get(merchantName);
        double totalPay = paymentRepo.getPaymentsByMerchant(merchant.getId());
        if (merchant.getMinSum() > totalPay) {
            merchant.setSentAmount(totalPay);
            merchant.setNeedToSend(0.00);
            merchant.setLastSent(LocalDate.now());
        } else {
            merchant.setNeedToSend(totalPay);
        }
        merchantRepo.update(merchant);
        System.out.println("The payment records for " + merchant.getName() + " was updated.");
    }
}