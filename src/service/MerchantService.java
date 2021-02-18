package service;

import entity.Merchant;
import repository.CustomerRepo;
import repository.MerchantRepo;
import repository.PaymentRepo;
import utils.DataFileReader;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MerchantService {
    private CustomerRepo customerRepo;
    private MerchantRepo merchantRepo;
    private PaymentRepo paymentRepo;

    public MerchantService() {
        this.customerRepo = new CustomerRepo();
        this.merchantRepo = new MerchantRepo();
        this.paymentRepo = new PaymentRepo();

        this.merchantRepo.setPaymentRepo(paymentRepo);
        this.paymentRepo.setCustomerRepo(customerRepo);
        this.paymentRepo.setMerchantRepo(merchantRepo);
    }

    public List<Merchant> getMerchantsFromFile() {
        String path = "C:\\Users\\astar\\IdeaProjects\\CashManagement\\merchants.dat";
        List<Merchant> merchantsList = new ArrayList<>();
        List<String> merchantsData = DataFileReader.getDataFromFile(path);
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

    public Merchant getByName(String merchantName) {
        return merchantRepo.getByName(merchantName, false);
    }

    public List<Merchant> getAll() {
        return merchantRepo.getAll();
    }

    //    public void sendPaymentToMerchant(String merchantName) {
//        Merchant merchant = merchantRepo.getByName(merchantName);
//        double totalPay = paymentRepo.getPaymentsByMerchantId(merchant.getId());
//        if (merchant.getMinSum() > totalPay) {
//            merchant.setNeedToSend(totalPay);
//            merchant.setSentAmount(0.00);
//            merchant.setLastSent(null);
//        } else {
//            merchant.setNeedToSend(0.00);
//            merchant.setSentAmount(totalPay);
//            merchant.setLastSent(LocalDate.now());
//        }
//        merchantRepo.update(merchant);
//        System.out.println("The payment records for " + merchant.getName() +
//                " was updated on " + LocalDate.now() + ".");
//    }
//
}