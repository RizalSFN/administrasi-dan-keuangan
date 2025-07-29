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
import model.SchoolIncome;

/**
 *
 * @author RIZAL
 */
public class SchoolIncomeDAO {

    private Connection conn;

    public SchoolIncomeDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean insertIncome(SchoolIncome income) {
        String sql = "INSERT INTO school_income (source_id, jumlah, tanggal_pemasukan, bukti_transaksi, keterangan, created_by) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, income.getSourceId());
            stmt.setBigDecimal(2, income.getJumlah());
            stmt.setDate(3, Date.valueOf(income.getTanggalPemasukan()));
            stmt.setString(4, income.getBuktiTransaksi());
            stmt.setString(5, income.getKeterangan());
            stmt.setInt(6, income.getCreatedBy());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<SchoolIncome> getAllIncome() {
        List<SchoolIncome> list = new ArrayList<>();
        String sql = "SELECT s.*, i.nama FROM school_income s JOIN income_source i ON s.source_id = i.id ORDER BY s.tanggal_pemasukan DESC";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                SchoolIncome income = new SchoolIncome();
                income.setId(rs.getInt("id"));
                income.setNama(rs.getString("nama"));
                income.setJumlah(rs.getBigDecimal("jumlah"));
                income.setTanggalPemasukan(rs.getDate("tanggal_pemasukan").toLocalDate());
                income.setBuktiTransaksi(rs.getString("bukti_transaksi"));
                income.setKeterangan(rs.getString("keterangan"));

                list.add(income);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public int getLastInsertedId() {
        int id = -1;
        String sql = "SELECT id FROM school_income ORDER BY id DESC LIMIT 1";

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
