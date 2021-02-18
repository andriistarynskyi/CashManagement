package service;

import entity.Customer;
import repository.CustomerRepo;
import repository.MerchantRepo;
import repository.PaymentRepo;
import utils.DataFileReader;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    private CustomerRepo customerRepo;
    private PaymentRepo paymentRepo;
    private MerchantRepo merchantRepo;

    public CustomerService() {
        this.customerRepo = new CustomerRepo();
        this.merchantRepo = new MerchantRepo();
        this.paymentRepo = new PaymentRepo();

        this.customerRepo.setPaymentRepo(paymentRepo);
        this.paymentRepo.setMerchantRepo(merchantRepo);
        this.paymentRepo.setCustomerRepo(customerRepo);
    }

    public List<Customer> getCustomersFromFile() {
        String path = "C:\\Users\\astar\\IdeaProjects\\CashManagement\\customers.dat";
        List<Customer> customerList = new ArrayList<>();
        List<String> customersData = DataFileReader.getDataFromFile(path);
        for (String str : customersData) {
            String[] tempArray = str.split(",");
            String name = tempArray[0];
            String address = tempArray[1];
            String email = tempArray[2];
            String ccNo = tempArray[3];
            String ccType = tempArray[4];
            LocalDate maturity = parseDate(tempArray[5]);
            customerList.add(new Customer(name, address, email, ccNo, ccType, maturity));
        }
        return customerList;
    }

    public LocalDate parseDate(String str) {
        LocalDate date;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/M/dd");
        date = LocalDate.parse(str, formatter);
        return date;
    }

    public Customer getByName(String name) {
        return customerRepo.getByName(name, false);
    }

    public Customer getById(int id) {
        return customerRepo.getById(id, false);
    }

    public List<Customer> getAll() {
        return customerRepo.getAll();
    }

    public boolean save(Customer customer) {
        customerRepo.save(customer);
        return true;
    }
}