package by.klubnikov.service;

import by.klubnikov.model.Student;
import by.klubnikov.repository.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepo repository;


    public List<Student> findAll() {
        return repository.findAll();
    }


    public Student findById(Integer id) {
        return repository.findById(id).orElseThrow();
    }


    public Student save(Student student) {
        repository.save(student);
        return student;
    }


    public void update(Student student) {
        repository.update(student);
    }


    public void delete(Integer id) {
        repository.delete(id);
    }
}
