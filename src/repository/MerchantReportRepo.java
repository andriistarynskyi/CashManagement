package repository;

import entitites.Merchant;
import entitites.report.MerchantReport;
import repository.utils.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MerchantReportRepo {

    MerchantRepo merchantRepo = new MerchantRepo();

    public List<MerchantReport> getMerChantReport() {
        List<MerchantReport> merchantReports = new ArrayList<>();

        String sqlQuery = "SELECT m.name           AS merchantName,\n" +
                "       SUM(p.sumPaid) AS totalPaid,\n" +
                "       m.lastSent     AS lastSent\n" +
                "FROM merchants m,\n" +
                "     payments p\n" +
                "WHERE m.id = p.merchantId\n" +
                "GROUP BY m.name, m.id\n" +
                "ORDER BY m.id";
        try (
                Connection conn = DbConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(sqlQuery);
        ) {
            ResultSet rs = statement.executeQuery();
            Merchant merchant;
            while (rs.next()) {
                String merchantName = rs.getString("merchantName");
                double sumPaid = rs.getDouble("totalPaid");
                double lastSent = rs.getDouble("lastSent");

                merchantReports.add(new MerchantReport(merchantRepo.getByName(merchantName), sumPaid, lastSent));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return merchantReports;
    }
}