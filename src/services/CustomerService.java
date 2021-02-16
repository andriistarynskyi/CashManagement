package services;

import entitites.Customer;
import repository.CustomerRepo;
import utils.DataFileReader;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {

    CustomerRepo customerRepo = new CustomerRepo();

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
        Customer customer = customerRepo.getByName(name);
        return customer;
    }
}