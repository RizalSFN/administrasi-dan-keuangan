package controller;

import model.Student;
import model.DAO.StudentDAO;
import config.DatabaseConnection;
import java.sql.Connection;

public class StudentController {
    private StudentDAO studentDAO;

    public StudentController() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            studentDAO = new StudentDAO(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean createStudent(int userId, String namaLengkap, String nisn, String kelas) {
        Student student = new Student();
        student.setUserId(userId);
        student.setNamaLengkap(namaLengkap);
        student.setNisn(nisn);
        student.setKelas(kelas);

        return studentDAO.insertNewStudent(student);
    }

    public Student getStudentByUserId(int userId) {
        return studentDAO.findByUserId(userId);
    }

    public Student getStudentByNamaLengkap(String namaLengkap) {
        return studentDAO.findByNamaLengkap(namaLengkap);
    }

    public Student getStudentByNisn(String nisn) {
        return studentDAO.findByNisn(nisn);
    }

    public Student getStudentByKelas(String kelas) {
        return studentDAO.findByKelas(kelas);
    }

    
}
