package repository;

import entitites.Payment;
import repository.utils.DbConnection;

import java.sql.*;

public class PaymentRepo {

    MerchantRepo merchantRepo = new MerchantRepo();
    CustomerRepo customerRepo = new CustomerRepo();

    public void save(Payment p) {
        String sqlQuery = "INSERT INTO payments (dt, merchantId, customerId, goods, " +
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
            statement.executeUpdate();
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
            String goods = rs.getString("goods");
            double sumPaid = rs.getDouble("sumPaid");
            double chargePaid = rs.getDouble("chargePaid");

            payment = new Payment(id, td, merchantRepo.getById(merchantId),
                    customerRepo.getById(customerId), goods, sumPaid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payment;
    }
}