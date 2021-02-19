package reportService;

import entity.Customer;
import entity.Payment;
import service.CustomerService;
import service.PaymentService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomerReportService {
    PaymentService paymentService = new PaymentService();
    CustomerService customerService = new CustomerService();

    public Customer getMostActiveCustomer(LocalDate startDate, LocalDate endDate) {
        List<Payment> payments = getPaymentsWithinTimePeriod(startDate, endDate);
        List<Integer> customersIds = getCustomerIds(payments);
        int customerId = getMostCommonCustomerId(customersIds);
        return customerService.getById(customerId);
    }

    public List<Payment> getPaymentsWithinTimePeriod(LocalDate startDate, LocalDate endDate) {
        List<Payment> payments = new ArrayList<>();
        for (Payment p : paymentService.getAll()) {
            if (p.getDate().toLocalDateTime().toLocalDate().isAfter(startDate) &&
                    p.getDate().toLocalDateTime().toLocalDate().isBefore(endDate)) {
                payments.add(p);
            }
        }
        return payments;
    }

    private List<Integer> getCustomerIds(List<Payment> paymentsWithinInterval) {
        List<Integer> customerIds = new ArrayList<>();
        for (Payment p : paymentsWithinInterval) {
            customerIds.add(p.getCustomer().getId());
        }
        return customerIds;
    }

    private Integer getMostCommonCustomerId(List<Integer> customerIds) {
        Collections.sort(customerIds);
        int mostCommonId = 0;
        int lastId = 0;
        int mostCount = 0;
        int lastCount = 0;
        for (Integer i : customerIds) {
            if (i == lastId) {
                lastCount++;
            } else if (lastCount > mostCount) {
                mostCount = lastCount;
                mostCommonId = lastId;
            }
            lastId = i;
        }
        return mostCommonId;
    }
}