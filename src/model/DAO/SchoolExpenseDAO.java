package model.DAO;

import model.SchoolExpense;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SchoolExpenseDAO {
    private Connection conn;

    public SchoolExpenseDAO(Connection conn) {
        this.conn = conn;
    }

    
}
