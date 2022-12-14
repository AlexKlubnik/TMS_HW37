package by.klubnikov.service;

import by.klubnikov.model.Student;
import by.klubnikov.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements CrudService<Student, Integer> {

    private CourseService courseService;

    private StudentRepo studentRepo;

    @Autowired
    public StudentService(StudentRepo studentRepo, CourseService courseService) {

        this.courseService = courseService;
        this.studentRepo = studentRepo;
    }

    @Override
    public List<Student> findAll() {
        return studentRepo.findAll();
    }

    @Override
    public Optional<Student> findById(Integer id) {
        return studentRepo.findById(id);
    }

    @Override
    public Student save(Student entity) {
        studentRepo.save(entity);
        return entity;
    }

    @Override
    public void delete(Student entity) {
        studentRepo.delete(entity);
    }
}
