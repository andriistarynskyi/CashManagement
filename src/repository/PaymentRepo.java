package repository;

import entitites.Payment;
import repository.utils.DbConnection;
import services.PaymentService;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PaymentRepo {

    PaymentService paymentService = new PaymentService();
    MerchantRepo merchantRepo = new MerchantRepo();
    CustomerRepo customerRepo = new CustomerRepo();

    public void saveAll(Set<Payment> paymentList) {
        for (Payment payment : paymentList) {
            save(payment);
        }
    }

    public void save(Payment p) {
        String sqlQuery = "INSERT INTO payments (dt, merchantId, customerId, goods, " +
                "sumPaid, chargePaid) values(?, ?, ?, ?, ?, ?)";
        try (
                Connection conn = DbConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(sqlQuery);
        ) {
            statement.setTimestamp(1, p.getDate());
            statement.setInt(2, p.getMerchant().getId());
            statement.setInt(3, p.getCustomer().getId());
            statement.setString(4, p.getProductName());
            statement.setDouble(5, p.getSumPaid());
            statement.setDouble(6, paymentService.calculateCharge(p).getChargePaid());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public double getPaymentsByMerchantId(int merchantId) {
        double totalPay = 0;
        String sqlQuery = "SELECT SUM(p.sumPaid) AS allPayments " +
                "FROM payments p, merchants m " +
                "WHERE m.id = p.merchantId AND " +
                "m.id = ? AND m.lastSent IS NULL OR p.dt > m.lastSent;\n";
        try (
                Connection conn = DbConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(sqlQuery);
        ) {
            statement.setInt(1, merchantId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                totalPay = rs.getDouble("allPayments");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return totalPay;
    }

    public List<Payment> getPaymentsByCustomerIdWithinTimeFrame(int customerId,
                                                                LocalDate startDate,
                                                                LocalDate endDate) {
        List<Payment> paymentsList = new ArrayList<>();
        Payment p = null;
        String sqlQuery = "SELECT * FROM payments WHERE customerId = ? AND dt > ? and dt < ?";

        try (
                Connection conn = DbConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(sqlQuery);
        ) {
            statement.setInt(1, customerId);
            statement.setDate(2, Date.valueOf(startDate));
            statement.setDate(3, Date.valueOf(endDate));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                p = getPayment(rs);
                paymentsList.add(p);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return paymentsList;
    }

    private Payment getPayment(ResultSet rs) throws SQLException {
        Payment payment = new Payment();
        int id = rs.getInt("id");
        Timestamp dt = rs.getTimestamp("dt");
        int merchantId = rs.getInt("merchantId");
        int customerId = rs.getInt("customerId");
        String productName = rs.getString("goods");
        double sumPaid = rs.getDouble("sumPaid");
        double chargePaid = rs.getDouble("chargePaid");

        payment = new Payment(id, dt, merchantRepo.getById(merchantId),
                customerRepo.getById(customerId), productName,
                sumPaid, chargePaid);
        return payment;
    }
}