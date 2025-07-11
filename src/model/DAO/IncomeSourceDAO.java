package model.DAO;

import model.IncomeSource;
import java.sql.Connection;
import java.sql.Date;
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
        
    }
}
