package repository;

import DBUtils.DbConnection;
import entity.Customer;
import entity.Merchant;
import entity.Payment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentRepo {
    private CustomerRepo customerRepo;
    private MerchantRepo merchantRepo;

    public void setCustomerRepo(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    public void setMerchantRepo(MerchantRepo merchantRepo) {
        this.merchantRepo = merchantRepo;
    }

    public void save(Payment p) {
        String sqlQuery = "INSERT INTO payments (dt, merchantId, customerId, name, " +
                "sumPaid) values(?, ?, ?, ?, ?)";
        try (
                Connection conn = DbConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(sqlQuery);
        ) {
            statement.setTimestamp(1, p.getDate());
            statement.setInt(2, p.getMerchant().getId());
            statement.setInt(3, p.getCustomer().getId());
            statement.setString(4, p.getProductName());
            statement.setDouble(5, p.getSumPaid());
            statement.addBatch();
            statement.executeBatch();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    private Payment getPayment(ResultSet rs) {
        Payment payment = null;
        try {
            int id = rs.getInt("id");
            Timestamp td = rs.getTimestamp("dt");
            int merchantId = rs.getInt("merchantId");
            int customerId = rs.getInt("customerId");
            String goods = rs.getString("name");
            double sumPaid = rs.getDouble("sumPaid");
            double chargePaid = rs.getDouble("chargePaid");

            payment = new Payment(id, td,
                    merchantRepo.getById(merchantId, true),
                    customerRepo.getById(customerId, true),
                    goods, sumPaid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payment;
    }

    public List<Payment> getByCustomer(Customer customer) {
        List<Payment> paymentList = new ArrayList<>();
        String sqlQuery = "SELECT * FROM payments WHERE customerId=" + customer.getId();
        try (
                Connection conn = DbConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(sqlQuery);
        ) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Payment p = getPayment(rs);
                paymentList.add(p);
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return paymentList;
    }

    public List<Payment> getByMerchant(Merchant merchant) {
        List<Payment> payments = new ArrayList<>();
        String sqlQuery = "SELECT * FROM payments WHERE merchantId=" + merchant.getId();
        try (
                Connection conn = DbConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(sqlQuery);
        ) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Payment p = getPayment(rs);
                payments.add(p);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return payments;
    }

    public List<Payment> getAll() {
        List<Payment> paymentsList = new ArrayList<>();
        String sqlQuery = "SELECT * FROM payments";
        try (
                Connection conn = DbConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(sqlQuery);
        ) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Payment p = getPayment(rs);
                paymentsList.add(p);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return paymentsList;
    }
}