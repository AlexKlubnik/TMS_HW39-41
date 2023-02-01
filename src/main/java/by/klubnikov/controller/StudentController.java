package by.klubnikov.controller;

import by.klubnikov.model.Student;
import by.klubnikov.service.StudentService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("students")
@RequiredArgsConstructor
@Slf4j
public class StudentController {

    private final StudentService studentService;
    private int visitCounter;

    @GetMapping()
    public String findAll(HttpSession session, Model model) {
        visitCounter++;
        LocalDateTime timeOfGetAllStudents = LocalDateTime.now();
        List<Student> students = studentService.findAll();
        int quantityOfStudentsNow = students.size();

        session.setAttribute("visitCounter", visitCounter);
        session.setAttribute("students", students);
        session.setAttribute("time", timeOfGetAllStudents);
        session.setAttribute("quantity", quantityOfStudentsNow);
        return "students.jsp";
    }

    @GetMapping("/{id}")
    public String findById(HttpSession session,
                                 @PathVariable(value = "id") Integer id) {
            Student student = studentService.findById(id);
            int studentId = student.getId();
            String studentName = student.getName();
            String studentAddress = student.getAddress();
            session.setAttribute("id", studentId);
            session.setAttribute("name", studentName);
            session.setAttribute("address", studentAddress);

        return "student.jsp";
    }

    @GetMapping("/studentform")
    public String showForm() {
        return "student_form.jsp";
    }

    @PostMapping("/studentform")
    public String save(Model model,
                       @Valid Student student,
                       Errors errors) {

            if (errors.hasErrors()) {
                String nameError = Objects.requireNonNull(errors.getFieldError("name")).getDefaultMessage();
                String addressError = Objects.requireNonNull(errors.getFieldError("address")).getDefaultMessage();
                model.addAttribute("nameError", nameError);
                model.addAttribute("addressError", addressError);
                return "student_form.jsp";
            }

        studentService.save(student);
        return "redirect:/students";
    }

}








