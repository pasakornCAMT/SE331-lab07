package camt.cbsd.lab05.service;

import camt.cbsd.lab05.dao.StudentDao;
import camt.cbsd.lab05.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Profile("firstDataSource")
@ConfigurationProperties(prefix = "server")
@Service
public class StudentServiceImpl implements StudentService {
    String imageBaseUrl;
    String baseUrl;
    String imageUrl;
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @PostConstruct
    protected void setImageBaseUrl(){
        this.imageBaseUrl = this.baseUrl + this.imageUrl;
    }

    @Autowired
    StudentDao studentDao;
    public List<Student> getStudents(){

        return studentDao.getStudents();
    }

    @Override
    public Student findById(long id) {
        return studentDao.findById(id);
    }

    @Override
    public Student addStudent(Student student) {
        return studentDao.addStudent(student);
    }
}
