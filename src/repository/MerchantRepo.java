package repository;

import entitites.Merchant;
import repository.utils.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MerchantRepo {


    public void save(Merchant merchant) {
        String sqlQuery = "INSERT INTO merchants " +
                "(name, bankName, swift, account, charge, period, minSum, needToSend, sent, lastSent) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (
                Connection conn = DbConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(sqlQuery);
        ) {
            statement.setString(1, merchant.getName());
            statement.setString(2, merchant.getBankName());
            statement.setString(3, merchant.getSwift());
            statement.setString(4, merchant.getAccount());
            statement.setDouble(5, merchant.getCharge());
            statement.setInt(6, merchant.getPeriod());
            statement.setDouble(7, merchant.getMinSum());
            statement.setDouble(8, merchant.getNeedToSend());
            statement.setDouble(9, merchant.getSentAmount());
            if (merchant.getLastSent() == null) {
                statement.setNull(10, java.sql.Types.DATE);
            } else {
                statement.setDate(10, java.sql.Date.valueOf(merchant.getLastSent()));
            }
            statement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public Merchant getById(int id) {
        Merchant merchant = null;
        String sqlQuery = "SELECT * FROM merchants WHERE id = ?";
        try (
                Connection conn = DbConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(sqlQuery);
        ) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            merchant = getMerchant(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return merchant;
    }

    private Merchant getMerchant(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String merchantName = rs.getString("name");
        String bankName = rs.getString("bankName");
        String swift = rs.getString("swift");
        String account = rs.getString("account");
        double charge = rs.getDouble("charge");
        int period = rs.getInt("period");
        double minSum = rs.getDouble("minSum");
        double needToSend = rs.getDouble("needToSend");
        double sent = rs.getDouble("sent");
        LocalDate lastSent;
        if (rs.getDate("lastSent") == null) {
            lastSent = null;
        } else {
            lastSent = rs.getDate("lastSent").toLocalDate();
        }
        Merchant merchant = new Merchant(id, merchantName, bankName, swift, account,
                charge, period, minSum, needToSend, sent, lastSent);

        return merchant;
    }

    public void update(Merchant merchant) {
        String sqlQuery = "UPDATE merchants SET " +
                "needToSend = ?, sent = ?, lastSent = ? WHERE name = ?";

        try (
                Connection conn = DbConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(sqlQuery);
        ) {
            statement.setDouble(1, merchant.getNeedToSend());
            statement.setDouble(2, merchant.getSentAmount());
            if (merchant.getLastSent() == null) {
                statement.setNull(3, java.sql.Types.DATE);
            } else {
                statement.setDate(3, java.sql.Date.valueOf(merchant.getLastSent()));
            }
            statement.setString(4, merchant.getName());
            statement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public Merchant getByName(String merChantName) {
        Merchant merchant = null;
        String sqlQuery = "SELECT * FROM merchants WHERE name= ?";
        try (
                Connection conn = DbConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(sqlQuery);
        ) {
            statement.setString(1, merChantName);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                merchant = getMerchant(rs);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return merchant;
    }

    public List<Merchant> getAll() {
        List<Merchant> merchantList = new ArrayList<>();
        String sqlQuery = "SELECT * FROM merchants";

        try (
                Connection conn = DbConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(sqlQuery);
        ) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Merchant merchant = getMerchant(rs);
                merchantList.add(merchant);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return merchantList;
    }
}