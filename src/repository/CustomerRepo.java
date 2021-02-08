package repository;

import entitites.Customer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Set;

public class CustomerRepo {
    public void add(Set<Customer> customerList) throws IOException, SQLException {
        String sqlQuery = "INSERT INTO customers(name, address, email, ccNo, ccType, maturity) values(?, ?, ?, ?, ?, ? )";
        try (Connection conn = DbConnection.get();
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
        }
    }

    public Customer get(String name) throws IOException, SQLException {
        Customer customer = null;
        String sqlQuery = "SELECT * FROM customers WHERE name = ?";
        try (
                Connection conn = DbConnection.get();
                PreparedStatement statement = conn.prepareStatement(sqlQuery);
        ) {
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
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
        }
        return customer;
    }
}