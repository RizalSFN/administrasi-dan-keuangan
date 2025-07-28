package model.DAO;

import model.IncomeSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class IncomeSourceDAO {
    private Connection conn;

    public IncomeSourceDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean insertNewIncomeSource(IncomeSource incomeSource) {
        String sql = "INSERT INTO income_source (nama, deskripsi) VALUES (?, ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, incomeSource.getNama());
            stmt.setString(2, incomeSource.getDeskripsi());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<IncomeSource> getAllKategoriPemasukan() {
        List<IncomeSource> list = new ArrayList<>();
        String sql = "SELECT * FROM income_source";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                IncomeSource ic = new IncomeSource();
                ic.setId(rs.getInt("id"));
                ic.setNama(rs.getString("nama"));
                ic.setDeskripsi(rs.getString("deskripsi"));
                ic.setStatus(rs.getString("status"));
                list.add(ic);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public IncomeSource findByStatus(String status) {
        IncomeSource incomeSource = null;

        try {
            String sql = "SELECT * FROM income_source WHERE status = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, status);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                incomeSource = new IncomeSource();
                incomeSource.setId(rs.getInt("id"));
                incomeSource.setNama(rs.getString("nama"));
                incomeSource.setDeskripsi(rs.getString("deskripsi"));
                incomeSource.setStatus(rs.getString("status"));
                incomeSource.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            }
            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return incomeSource;
    }

    public boolean updateIncomeSource(IncomeSource incomeSource) {
        StringBuilder sql = new StringBuilder("UPDATE income_source SET ");
        List<Object> params = new ArrayList<>();

        if (incomeSource.getNama() != null && !incomeSource.getNama().isEmpty()) {
            sql.append("nama = ?, ");
            params.add(incomeSource.getNama());
        }

        if (incomeSource.getDeskripsi() != null && !incomeSource.getDeskripsi().isEmpty()) {
            sql.append("deskripsi = ?, ");
            params.add(incomeSource.getDeskripsi());
        }

        if (params.isEmpty()) {
            return false;
        }

        sql.setLength(sql.length() - 2);

        sql.append(" WHERE id = ?");
        params.add(incomeSource.getId());

        try {
            PreparedStatement stmt = conn.prepareStatement(sql.toString());

            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
