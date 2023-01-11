package by.klubnikov.repository;

import by.klubnikov.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class StudentRepo implements CrudRepository<Student, Integer> {

    private final String GET_ALL_STUDENTS_FROM_DB = "select * from students";
    private final String GET_STUDENT_BY_ID = "select id, name, address from students where id = ?";
    private final String SAVE_STUDENT = "insert into students (name, address) values (?,?) ";
    private final String UPDATE_STUDENT = "update students set name = ?, address = ? where id = ?";
    private final String DELETE_STUDENT = "delete from students where id = ?";

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Student> findAll() {
        return jdbcTemplate.query(GET_ALL_STUDENTS_FROM_DB,
                ((rs, rowNum) -> new Student(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("address"))));
    }

    @Override
    public Optional<Student> findById(Integer id) {

        Student student = jdbcTemplate.queryForObject(GET_STUDENT_BY_ID,
                (rs, rowNum) ->
                        new Student(rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("address")),
                id);
        return Optional.ofNullable(student);
    }

    @Override
    public Integer save(Student entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SAVE_STUDENT, new String[]{"id"});
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getAddress());
            return ps;
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public void update(Student entity) {
        jdbcTemplate.update(UPDATE_STUDENT,
                entity.getName(),
                entity.getAddress(),
                entity.getId());
    }

    @Override
    public void delete(Student entity) {
        jdbcTemplate.update(DELETE_STUDENT, entity.getId());
    }
}
