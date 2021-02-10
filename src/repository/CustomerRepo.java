package repository;

import entitites.Customer;
import repository.utils.DbConnection;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.Set;

public class CustomerRepo {
    public void saveAll(Set<Customer> customerList) throws IOException {
        String sqlQuery = "INSERT INTO customers(name, address, email, ccNo, ccType, maturity) values(?, ?, ?, ?, ?, ? )";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(sqlQuery);
        ) {
            for (Customer c : customerList) {
                statement.setString(1, c.getName());
                statement.setString(2, c.getAddress());
                statement.setString(3, c.getEmail());
                statement.setString(4, c.getCcNo());
                statement.setString(5, c.getCcType());
                statement.setDate(6, java.sql.Date.valueOf(c.getMaturity()));
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Customer getByName(String name){
        Customer customer = null;
        String sqlQuery = "SELECT * FROM customers WHERE name = ?";
        try (
                Connection conn = DbConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(sqlQuery);
        ) {
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            customer = getCustomer(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customer;
    }

    public Customer getById(int id) {
        Customer customer = null;
        String sqlQuery = "SELECT * FROM customers WHERE id = ?";
        try (
                Connection conn = DbConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(sqlQuery);
        ) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            customer = getCustomer(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customer;
    }

    private Customer getCustomer(ResultSet rs) throws SQLException {
        Customer customer = null;
        while (rs.next()) {
            int id = rs.getInt("id");
            String customerName = rs.getString("name");
            String address = rs.getString("address");
            String email = rs.getString("email");
            String ccNo = rs.getString("ccNo");
            String ccType = rs.getString("ccType");
            LocalDate maturity = rs.getDate("maturity").toLocalDate();
            customer = new Customer(id, customerName, address, email, ccNo, ccType, maturity);
        }
        return customer;
    }

    public Customer getMostActiveCustomerWithinTimeFrame(LocalDate startDate, LocalDate endDate) {
        Customer customer = null;
        String sql = "SELECT customerId, SUM(sumPaid) AS totalPaid FROM payments\n" +
                "WHERE dt > ? AND ? < dt\n" +
                "GROUP BY customerId\n" +
                "ORDER BY totalPaid DESC LIMIT 1;";

        try (
                Connection conn = DbConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {
            statement.setDate(1, Date.valueOf(startDate));
            statement.setDate(2, Date.valueOf(endDate));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int customerId = rs.getInt("customerId");
                customer = getById(customerId);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customer;
    }
}