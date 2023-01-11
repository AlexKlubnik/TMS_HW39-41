package by.klubnikov.service;

import by.klubnikov.model.Student;
import by.klubnikov.repository.CrudRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService implements CrudRepository<Student, Integer> {

    private final CrudRepository<Student, Integer> repository;

    @Override
    public List<Student> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Student> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Integer save(Student entity) {
        repository.save(entity);
        return entity.getId();
    }

    @Override
    public void update(Student entity) {
        repository.update(entity);
    }

    @Override
    public void delete(Student entity) {
        repository.delete(entity);
    }
}
