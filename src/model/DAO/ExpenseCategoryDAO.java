package model.DAO;

import model.ExpenseCategory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ExpenseCategoryDAO {
    private Connection conn;

    public ExpenseCategoryDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean insertNewExpenseCategory(ExpenseCategory expenseCategory) {
        String sql = "INSERT INTO expense_category (nama, deskripsi) VALUES (?, ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, expenseCategory.getNama());
            stmt.setString(2, expenseCategory.getDeskripsi());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ExpenseCategory findByStatus(String status) {
        ExpenseCategory expenseCategory = null;

        try {
            String sql = "SELECT * FROM expense_category WHERE status = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, status);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                expenseCategory = new ExpenseCategory();
                expenseCategory.setId(rs.getInt("id"));
                expenseCategory.setNama(rs.getString("nama"));
                expenseCategory.setDeskripsi(rs.getString("deskripsi"));
                expenseCategory.setStatus(rs.getString("status"));
                expenseCategory.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return expenseCategory;
    }

    public boolean updateExpenseCategory(ExpenseCategory expenseCategory) {
        StringBuilder sql = new StringBuilder("UPDATE expense_category SET ");
        List<Object> params = new ArrayList<>();

        if (expenseCategory.getNama() != null && !expenseCategory.getNama().isEmpty()) {
            sql.append("nama = ?, ");
            params.add(expenseCategory.getNama());
        }

        if (expenseCategory.getDeskripsi() != null && !expenseCategory.getDeskripsi().isEmpty()) {
            sql.append("deskripsi = ?, ");
            params.add(expenseCategory.getDeskripsi());
        }

        if (expenseCategory.getStatus() != null && !expenseCategory.getStatus().isEmpty()) {
            sql.append("status = ?, ");
            params.add(expenseCategory.getStatus());
        }

        if (params.isEmpty()) {
            return false;
        }

        sql.setLength(sql.length() - 2);

        sql.append(" WHERE id = ?");
        params.add(expenseCategory.getId());

        try {
            PreparedStatement stmt = conn.prepareStatement(sql.toString());

            for (int i = 1; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
