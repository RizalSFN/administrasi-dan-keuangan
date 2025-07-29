/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.SchoolExpense;

/**
 *
 * @author RIZAL
 */
public class SchoolExpenseDAO {

    private Connection conn;

    public SchoolExpenseDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean insertExpense(SchoolExpense expense) {
        String sql = "INSERT INTO school_expense (category_id, jumlah, tanggal_pengeluaran, bukti_transaksi, keterangan, created_by) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, expense.getCategoryId());
            stmt.setBigDecimal(2, expense.getJumlah());
            stmt.setDate(3, Date.valueOf(expense.getTanggalPengeluaran()));
            stmt.setString(4, expense.getBuktiTransaksi());
            stmt.setString(5, expense.getKeterangan());
            stmt.setInt(6, expense.getCreatedBy());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<SchoolExpense> getAllExpense() {
        List<SchoolExpense> list = new ArrayList<>();
        String sql = "SELECT s.*, i.nama FROM school_expense s JOIN expense_category i ON s.category_id = i.id ORDER BY s.tanggal_pengeluaran DESC";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                SchoolExpense expense = new SchoolExpense();
                expense.setId(rs.getInt("id"));
                expense.setNama(rs.getString("nama"));
                expense.setjumlah(rs.getBigDecimal("jumlah"));
                expense.setTanggalPengeluaran(rs.getDate("tanggal_pengeluaran").toLocalDate());
                expense.setBuktiTransaksi(rs.getString("bukti_transaksi"));
                expense.setKeterangan(rs.getString("keterangan"));

                list.add(expense);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public int getLastInsertedId() {
        int id = -1;
        String sql = "SELECT id FROM school_expense ORDER BY id DESC LIMIT 1";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;
    }
}
