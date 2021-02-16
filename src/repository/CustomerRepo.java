package repository;

import entitites.Customer;
import repository.utils.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class CustomerRepo {

    public void save(Customer customer) {
        String sqlQuery = "INSERT INTO customers(name, address, email, ccNo," +
                " ccType, maturity)values(?, ?, ?, ?, ?, ? )";
        try (
                Connection conn = DbConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(sqlQuery);
        ) {
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getAddress());
            statement.setString(3, customer.getEmail());
            statement.setString(4, customer.getCcNo());
            statement.setString(5, customer.getCcType());
            statement.setDate(6, java.sql.Date.valueOf(customer.getMaturity()));
            statement.addBatch();
            statement.executeBatch();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public Customer getByName(String name) {
        Customer customer = null;
        String sqlQuery = "SELECT * FROM customers WHERE name = ?";
        try (
                Connection conn = DbConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(sqlQuery);
        ) {
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            customer = getCustomer(rs);

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return customer;
    }

    public Customer getById(int id) {
        Customer customer = null;
        String sqlQuery = "SELECT * FROM customers WHERE id =" + id;
        try (
                Connection conn = DbConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(sqlQuery);
        ) {
            ResultSet rs = statement.executeQuery();
            customer = getCustomer(rs);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
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
}