package model.DAO;

import model.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.User;

public class StudentDAO {

    private Connection conn;

    public StudentDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean insertNewStudent(Student student) {
        String sql = "INSERT INTO student (user_id, nama_lengkap, nisn, kelas) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, student.getUserId());
            stmt.setString(2, student.getNamaLengkap());
            stmt.setString(3, student.getNisn());
            stmt.setString(4, student.getKelas());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Student> getAllStudent() {
        List<Student> studentList = new ArrayList<>();
        String sql = "SELECT * FROM student";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setUserId(rs.getInt("user_id"));
                student.setNamaLengkap(rs.getString("nama_lengkap"));
                student.setNisn(rs.getString("nisn"));
                student.setKelas(rs.getString("kelas"));

                studentList.add(student);
            }

            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return studentList;
    }

    public int getJumlahSiswa(String status) {
        int totalCount = 0;

        try {
            String sql = "SELECT COUNT(*) FROM student JOIN user ON student.user_id = user.id WHERE user.status = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, status);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                totalCount = rs.getInt(1);
            }
            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return totalCount;
    }

    public Student getStudentById(int studentId) {
        Student student = null;
        String sql = "SELECT s.*, u.email FROM student s JOIN user u ON s.user_id = u.id WHERE s.id = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                student = new Student();
                student.setId(rs.getInt("id"));
                student.setNamaLengkap(rs.getString("nama_lengkap"));
                student.setKelas(rs.getString("kelas"));
                student.setNisn(rs.getString("nisn"));
                student.setEmail(rs.getString("email"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student;
    }

    public Student findByUserId(int user_id) {
        Student student = null;

        try {
            String sql = "SELECT * FROM student WHERE user_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, user_id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                student = new Student();
                student.setId(rs.getInt("id"));
                student.setUserId(rs.getInt("user_id"));
                student.setNamaLengkap(rs.getString("nama_lengkap"));
                student.setNisn(rs.getString("nisn"));
                student.setKelas(rs.getString("kelas"));
                student.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            }
            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return student;
    }

    public Student findByNamaLengkap(String nama_lengkap) {
        Student student = null;

        try {
            String sql = "SELECT * FROM student WHERE nama_lengkap = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nama_lengkap);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                student = new Student();
                student.setId(rs.getInt("id"));
                student.setUserId(rs.getInt("user_id"));
                student.setNamaLengkap(rs.getString("nama_lengkap"));
                student.setNisn(rs.getString("nisn"));
                student.setKelas(rs.getString("kelas"));
                student.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            }
            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return student;
    }

    public int findStudentIdByNisn(String nisn) {
        int studentId = -1;

        try {
            String sql = "SELECT id FROM student WHERE nisn = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nisn);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                studentId = rs.getInt("id");
            }

            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return studentId;
    }

    public Student findByNisn(String nisn) {
        Student student = null;

        try {
            String sql = "SELECT * FROM student WHERE nisn = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nisn);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                student = new Student();
                student.setId(rs.getInt("id"));
                student.setUserId(rs.getInt("user_id"));
                student.setNamaLengkap(rs.getString("nama_lengkap"));
                student.setNisn(rs.getString("nisn"));
                student.setKelas(rs.getString("kelas"));
                student.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            }
            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return student;
    }

    public Student findByKelas(String kelas) {
        Student student = null;

        try {
            String sql = "SELECT * FROM student WHERE kelas = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, kelas);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                student = new Student();
                student.setId(rs.getInt("id"));
                student.setUserId(rs.getInt("user_id"));
                student.setNamaLengkap(rs.getString("nama_lengkap"));
                student.setNisn(rs.getString("nisn"));
                student.setKelas(rs.getString("kelas"));
                student.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            }
            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return student;
    }

    public boolean updateStudent(Student student) {
        StringBuilder sql = new StringBuilder("UPDATE student SET ");
        List<Object> params = new ArrayList<>();

        if (student.getNamaLengkap() != null && !student.getNamaLengkap().isEmpty()) {
            sql.append("nama_lengkap = ?, ");
            params.add(student.getNamaLengkap());
        }

        if (student.getNisn() != null && !student.getNisn().isEmpty()) {
            sql.append("nisn = ?, ");
            params.add(student.getNisn());
        }

        if (student.getKelas() != null && !student.getKelas().isEmpty()) {
            sql.append("kelas = ?, ");
            params.add(student.getKelas());
        }

        if (params.isEmpty()) {
            return false;
        }

        sql.setLength(sql.length() - 2);

        sql.append(" WHERE id = ?");
        params.add(student.getId());

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
