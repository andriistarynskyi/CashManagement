package repository;

import entitites.Payment;
import repository.utils.DbConnection;

import java.io.IOException;
import java.sql.*;
import java.util.Set;

public class PaymentRepo {
    public void addAll(Set<Payment> paymentList){
        String sqlQuery = "INSERT INTO payments " +
                "(dt, merchantId, customerId, goods, sumPaid, chargePaid) " +
                "VALUES(?, ?, ?, ?, ?, ?)";
        try (
                Connection conn = DbConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(sqlQuery);
        ) {
            for (Payment p : paymentList) {
                statement.setTimestamp(1, p.getDate());
                statement.setInt(2, p.getMerchant().getId());
                statement.setInt(3, p.getCustomer().getId());
                statement.setString(4, p.getProductName());
                statement.setDouble(5, p.getSumPaid());
                statement.setDouble(6, p.getChargePaid());
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addPayment(Timestamp timestamp, int merchantId,
                           int customerId, String goods, double sumPaid,
                           double chargePaid) throws IOException, SQLException {
        String sqlQuery = "INSERT INTO payments (dt, merchantId, customerId, goods, " +
                "sumPaid, chargePaid) values(?, ?, ?, ?, ?, ?)";
        try (
                Connection conn = DbConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(sqlQuery);
        ) {
            statement.setTimestamp(1, timestamp);
            statement.setInt(2, merchantId);
            statement.setInt(3, customerId);
            statement.setString(4, goods);
            statement.setDouble(5, sumPaid);
            statement.setDouble(6, chargePaid);
            statement.executeUpdate();
        }
    }

    public double getPaymentsByMerchantId(int merchantId){
        double totalPay = 0;
        String sqlQuery = "SELECT SUM(sumPaid) AS allPayments FROM payments WHERE merchantId = ?";
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
}