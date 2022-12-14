package by.klubnikov.repository;

import by.klubnikov.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepo implements CrudRepository<Student, Integer> {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Student> findAll() {
        return jdbcTemplate.query("select * from students", new BeanPropertyRowMapper<>(Student.class));
    }

    @Override
    public Optional<Student> findById(Integer id) {
        return jdbcTemplate.query("select * from courses where id = ?",
                new BeanPropertyRowMapper<>(Student.class),
                id).stream()
                .findAny();
    }

    @Override
    public Student save(Student entity) {
        jdbcTemplate.update("insert into students (name, address) values (?,?)",
                entity.getName(), entity.getAddress());
        return entity;
    }

    @Override
    public void delete(Student entity) {
        jdbcTemplate.update("delete from students where id = ?", entity.getId());
    }
}
