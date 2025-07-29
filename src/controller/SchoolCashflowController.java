package controller;

import model.SchoolCashflow;
import model.DAO.SchoolCashflowDAO;
import config.DatabaseConnection;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SchoolCashflowController {

    private SchoolCashflowDAO schoolCashflowDAO;

    public SchoolCashflowController() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            schoolCashflowDAO = new SchoolCashflowDAO(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean createSchoolCashflow(SchoolCashflow schoolCashflow) {
        return schoolCashflowDAO.insertNewSchoolCashflow(schoolCashflow);
    }

    public static BigDecimal[] hitungLabaRugi(Date awal, Date akhir) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String tglAwal = sdf.format(awal);
        String tglAkhir = sdf.format(akhir);

        try (Connection conn = DatabaseConnection.getConnection()) {
            BigDecimal pemasukan = SchoolCashflowDAO.getTotalPemasukan(conn, tglAwal, tglAkhir);
            BigDecimal pengeluaran = SchoolCashflowDAO.getTotalPengeluaran(conn, tglAwal, tglAkhir);
            BigDecimal labaRugi = pemasukan.subtract(pengeluaran);
            return new BigDecimal[]{pemasukan, pengeluaran, labaRugi};
        }
    }

    public List<SchoolCashflow> getCashflowBetween(Date tglAwal, Date tglAkhir) {
        return schoolCashflowDAO.getCashFlowBetween(tglAwal, tglAkhir);
    }

    public BigDecimal getLastSaldo() {
        return schoolCashflowDAO.getLatestSaldo();
    }

    public Map<String, BigDecimal> hitungNeraca(String tanggal) throws SQLException {
        Map<String, BigDecimal> hasil = new HashMap<>();
        BigDecimal asetKas = schoolCashflowDAO.getSaldoKas(tanggal);
        BigDecimal asetPiutang = schoolCashflowDAO.getTotalPiutang(tanggal);
        BigDecimal pemasukan = schoolCashflowDAO.getTotalPemasukan(tanggal);
        BigDecimal pengeluaran = schoolCashflowDAO.getTotalPengeluaran(tanggal);
        BigDecimal ekuitas = pemasukan.subtract(pengeluaran);
        BigDecimal totalAset = asetKas.add(asetPiutang);
        BigDecimal totalKE = ekuitas;

        hasil.put("asetKas", asetKas);
        hasil.put("asetPiutang", asetPiutang);
        hasil.put("totalAset", totalAset);
        hasil.put("kewajiban", BigDecimal.ZERO);
        hasil.put("ekuitas", ekuitas);
        hasil.put("totalKE", totalKE);

        return hasil;
    }
}
