package repository;

import entitites.Merchant;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MerchantRepo {
    public void add(List<Merchant> merchantList) throws IOException, SQLException {

        for (Merchant m : merchantList) {
            String sqlQuery = "INSERT IGNORE INTO merchants " +
                    "(name, bankName, swift, account, charge, period, minSum, needToSend, sent, lastSent) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (
                    Connection conn = DbConnection.get();
                    PreparedStatement statement = conn.prepareStatement(sqlQuery);
            ) {
                statement.setString(1, m.getName());
                statement.setString(2, m.getBankName());
                statement.setString(3, m.getSwift());
                statement.setString(4, m.getAccount());
                statement.setDouble(5, m.getCharge());
                statement.setInt(6, m.getPeriod());
                statement.setDouble(7, m.getMinSum());
                statement.setDouble(8, m.getNeedToSend());
                statement.setDouble(9, m.getSentAmount());
                if (m.getLastSent() == null) {
                    statement.setNull(10, java.sql.Types.DATE);
                } else {
                    statement.setDate(10, java.sql.Date.valueOf(m.getLastSent()));
                }
                statement.addBatch();
                statement.executeBatch();
            }
        }
    }

    public Merchant get(String name) throws IOException, SQLException {
        Merchant merchant = null;
        String sqlQuery = "SELECT * FROM merchants WHERE name = ?";
        try (
                Connection conn = DbConnection.get();
                PreparedStatement statement = conn.prepareStatement(sqlQuery);
        ) {
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                merchant = getMerchant(rs);
            }
        }
        return merchant;
    }

    public Merchant getMerchant(ResultSet rs) throws SQLException {
        Merchant merchant = null;
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
        merchant = new Merchant(id, merchantName, bankName, swift, account,
                charge, period, minSum, needToSend, sent, lastSent);

        return merchant;
    }

    public List<Merchant> getSortedList() throws IOException, SQLException {
        List<Merchant> sortedMerchantsList = new ArrayList<>();
        String sqlQuery = "SELECT * FROM merchants ORDER BY name";
        try (
                Connection conn = DbConnection.get();
                PreparedStatement statement = conn.prepareStatement(sqlQuery);
        ) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Merchant merchant = getMerchant(rs);
                sortedMerchantsList.add(merchant);
            }
        }
        return sortedMerchantsList;
    }

    public void update(Merchant merchant) throws IOException, SQLException {
        String sqlQuery = "UPDATE merchants SET needToSend = ?, sent = ?, lastSent = ? WHERE name = ?";

        try (
                Connection conn = DbConnection.get();
                PreparedStatement statement = conn.prepareStatement(sqlQuery);
        ) {
            statement.setDouble(1, merchant.getSentAmount());
            statement.setDouble(2, merchant.getNeedToSend());
            if (merchant.getLastSent() == null) {
                statement.setNull(3, java.sql.Types.DATE);
            } else {
                statement.setDate(3, java.sql.Date.valueOf(merchant.getLastSent()));
            }
            statement.setString(4, merchant.getName());
            statement.executeUpdate();
        }
    }
}