package controller;

import model.Student;
import model.DAO.StudentDAO;
import config.DatabaseConnection;
import java.sql.Connection;
import java.util.List;

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

    public boolean createStudent(Student student) {
        return studentDAO.insertNewStudent(student);
    }
    
    public List<Student> getAllStudent() {
        return studentDAO.getAllStudent();
    }
    
    public int getJumlahSiswa(String status) {
        return studentDAO.getJumlahSiswa(status);
    }

    public Student getStudentById(int studentId) {
        return studentDAO.getStudentById(studentId);
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

    public boolean updateStudent(Student student) {
        return studentDAO.updateStudent(student);
    }
}
