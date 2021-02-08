package repository;

import entitites.MerchantReport;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MerchantReportRepo {
    public List<MerchantReport> getMerChantReport() throws SQLException, IOException {
        List<MerchantReport> merchantReports = new ArrayList<>();

        String sqlQuery = "SELECT m.id           AS merchantId,\n" +
                "       m.name         AS merchantName,\n" +
                "       SUM(p.sumPaid) AS totalPaid,\n" +
                "       m.lastSent     AS lastSent\n" +
                "FROM merchants m,\n" +
                "     payments p\n" +
                "WHERE m.id = p.merchantId\n" +
                "GROUP BY m.name, m.id\n" +
                "ORDER BY m.id;";
        try (
                Connection conn = DbConnection.get();
                PreparedStatement statement = conn.prepareStatement(sqlQuery);
        ) {
            ResultSet rs = statement.executeQuery();
            MerchantReport merchantReport;
            while (rs.next()) {
                int id = rs.getInt("merchantId");
                String name = rs.getString("merchantName");
                double sumPaid = rs.getDouble("totalPaid");
                double lastSent = rs.getDouble("lastSent");

                merchantReports.add(new MerchantReport(id, name, sumPaid, lastSent));
            }
        }
        return merchantReports;
    }
}