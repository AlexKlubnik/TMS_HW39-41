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

    private final String GET_ALL_STUDENTS_FROM_DB_SQL = "select * from students";
    private final String GET_STUDENT_BY_ID_SQL = "select id, name, address from students where id = ?";
    private final String SAVE_STUDENT_SQL = "insert into students (name, address) values (?,?) ";
    private final String UPDATE_STUDENT_SQL = "update students set name = ?, address = ? where id = ?";
    private final String DELETE_STUDENT_SQL = "delete from students where id = ?";

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Student> findAll() {
        return jdbcTemplate.query(GET_ALL_STUDENTS_FROM_DB_SQL,
                ((rs, rowNum) -> new Student(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("address"))));
    }

    @Override
    public Optional<Student> findById(Integer id) {

        Student student = jdbcTemplate.queryForObject(GET_STUDENT_BY_ID_SQL,
                (rs, rowNum) ->
                        new Student(rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("address")),
                id);
        return Optional.ofNullable(student);
    }

    @Override
    public Student save(Student student) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SAVE_STUDENT_SQL, new String[]{"id"});
            ps.setString(1, student.getName());
            ps.setString(2, student.getAddress());
            return ps;
        }, keyHolder);
        int id = keyHolder.getKey().intValue();
        student.setId(id);
        return student;
    }

    @Override
    public void update(Student student) {
        jdbcTemplate.update(UPDATE_STUDENT_SQL,
                student.getName(),
                student.getAddress(),
                student.getId());
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update(DELETE_STUDENT_SQL, id);
    }
}
