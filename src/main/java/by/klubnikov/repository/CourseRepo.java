package by.klubnikov.repository;

import by.klubnikov.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CourseRepo implements CrudRepository<Course, Integer> {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CourseRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Course> findAll() {
        return jdbcTemplate.query("select * from courses", new BeanPropertyRowMapper<>(Course.class));
    }

    @Override
    public Optional<Course> findById(Integer id) {
        return jdbcTemplate.query("select * from courses where id = ?",
                        (rs, rowNum) -> new Course(rs.getInt("id"),
                                rs.getString("description"),
                                rs.getInt("students")), id).stream()
                .findAny();
    }

    @Override
    public Course save(Course entity) {
        jdbcTemplate.update("insert into courses (description, students) values (?, ?)",
                entity.getDescription(), entity.getStudents());
        return entity;
    }

    @Override
    public void delete(Course entity) {
        jdbcTemplate.update("delete from courses where id= ?", entity.getId());
    }
}
