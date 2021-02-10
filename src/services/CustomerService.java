package services;

import entitites.Customer;
import entitites.Payment;
import repository.CustomerRepo;
import repository.PaymentRepo;
import utils.ReadDataFromFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomerService {

    CustomerRepo customerRepo = new CustomerRepo();
    PaymentRepo paymentRepo = new PaymentRepo();

    public Set<Customer> getCustomersFromFile() {
        String path = "C:\\Users\\astar\\IdeaProjects\\CashManagement\\customers.dat";
        Set<Customer> customerList = new HashSet<>();
        List<String> customersData = ReadDataFromFile.getFromFile(path);
        for (String str : customersData) {
            String[] tempArray = str.split(",");
            String name = tempArray[0];
            String address = tempArray[1];
            String email = tempArray[2];
            String ccNo = tempArray[3];
            String ccType = tempArray[4];
            LocalDate maturity = getMaturityDate(tempArray[5]);
            customerList.add(new Customer(name, address, email, ccNo, ccType, maturity));
        }
        return customerList;
    }

    public LocalDate getMaturityDate(String str) {
        LocalDate date;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/M/dd");
        date = LocalDate.parse(str, formatter);
        return date;
    }

    public Customer getMostActiveCustomer(LocalDate startDate, LocalDate endDate) {
        Customer customer;
        customer = customerRepo.getMostActiveCustomerWithinTimeFrame(startDate, endDate);
        List<Payment> paymentList = paymentRepo.getPaymentsByCustomerIdWithinTimeFrame(customer.getId(),
                startDate, endDate);
        customer.setPaymentsList(paymentList);
        return customer;
    }
}