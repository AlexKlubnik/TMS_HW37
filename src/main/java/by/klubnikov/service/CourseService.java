package by.klubnikov.service;

import by.klubnikov.model.Course;
import by.klubnikov.repository.CrudRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CourseService implements CrudRepository<Course, Integer> {

    private CrudRepository<Course, Integer> repository;

    public CourseService(@Qualifier("courseRepo") CrudRepository<Course, Integer> repository) {
        this.repository = repository;
    }

    @Override
    public List<Course> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Course> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Course save(Course entity) {
        repository.save(entity);
        return entity;
    }

    @Override
    public void delete(Course entity) {
        repository.delete(entity);
    }
}
