import entity.Customer;
import entity.Merchant;
import entity.Payment;
import service.CustomerService;
import service.MerchantService;
import service.PaymentService;

public class ExecuteMethods {

    public void displayResults() {
        CustomerService customerService = new CustomerService();
        PaymentService paymentService = new PaymentService();
        MerchantService merchantService = new MerchantService();

        for (Payment p : paymentService.getAll()) {
            System.out.println(p);
        }
        System.out.println("-----------------------------------");

        for (Merchant m : merchantService.getAll()) {
            System.out.println(m);
        }
        System.out.println("-----------------------------------");

        for (Customer c : customerService.getAll()) {
            System.out.println(c);
        }
    }
}
