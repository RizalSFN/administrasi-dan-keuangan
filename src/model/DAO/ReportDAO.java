package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReportDAO {

    private Connection conn;

    public ReportDAO(Connection conn) {
        this.conn = conn;
    }

    public float getTotalIncome() {
        float total = 0;

        try {
            String sql = "SELECT SUM(jumlah) FROM school_income";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                total = rs.getFloat(1);
            }
            stmt.close();
            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return total;
    }

    public float getTotalExpense() {
        float total = 0;

        try {
            String sql = "SELECT SUM(jumlah) FROM school_expense";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if  (rs.next()) {
                total = rs.getFloat(1);
            }
            stmt.close();
            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return total;
    }
}
