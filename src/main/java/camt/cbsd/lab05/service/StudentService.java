package camt.cbsd.lab05.service;

import camt.cbsd.lab05.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> getStudents();
    Student findById(long id);
    Student addStudent(Student student);
}
