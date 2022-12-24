package by.klubnikov.repository;

import by.klubnikov.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepo implements CrudRepository<Student, Integer> {

    public static final String INSERT_NEW_STUDENT = "insert into students (name, address) values (?,?)";
    public static final String UPDATE_STUDENT = "update students set name = ?, address = ? where id = ?";
    public static final String GET_STUDENT_BY_ID = "select * from courses where id = ?";
    public static final String GET_ALL_STUDENTS = "select * from students";
    public static final String DELETE_STUDENT_BY_ID = "delete from students where id = ?";
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Student> findAll() {
        return jdbcTemplate.query(GET_ALL_STUDENTS, new BeanPropertyRowMapper<>(Student.class));
    }

    @Override
    public Optional<Student> findById(Integer id) {
        return jdbcTemplate.query(GET_STUDENT_BY_ID,
                        new BeanPropertyRowMapper<>(Student.class), id)
                .stream()
                .filter(student -> student.getId() == id)
                .findAny();
    }

    @Override
    public Student save(Student entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Optional<Student> studentOptional = findById(entity.getId());

        if (studentOptional.isPresent()) {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(UPDATE_STUDENT);
                ps.setString(1, entity.getName());
                ps.setString(2, entity.getAddress());
                ps.setInt(3, entity.getId());
                return ps;
            }, keyHolder);
        } else {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(INSERT_NEW_STUDENT);
                ps.setString(1, entity.getName());
                ps.setString(2, entity.getAddress());
                return ps;
            }, keyHolder);
        }

        entity.setId((int) keyHolder.getKey());
        return entity;
    }

    @Override
    public void delete(Student entity) {
        jdbcTemplate.update(DELETE_STUDENT_BY_ID, entity.getId());
    }
}
