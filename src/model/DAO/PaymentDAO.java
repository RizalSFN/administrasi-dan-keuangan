package model.DAO;

import model.Payment;
import config.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {

    private Connection conn;

    public PaymentDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean insertNewPayment(Payment payment) {
        return false;
    }

}
