package model.DAO;

import model.SchoolCashflow;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SchoolCashflowDAO {
    private Connection conn;

    public SchoolCashflowDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean insertNewSchoolCashflow(SchoolCashflow schoolCashflow) {

    }

    public SchoolCashflow findByTipe(String tipe) {

    }

    public SchoolCashflow findByTanggal(Date tanggal) {

    }
}
