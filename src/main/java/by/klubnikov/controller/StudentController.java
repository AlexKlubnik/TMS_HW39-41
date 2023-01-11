package by.klubnikov.controller;

import by.klubnikov.model.Student;
import by.klubnikov.service.StudentService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private int visitCounter;

    @GetMapping("students")
    public String getAllStudents(HttpSession session, Model model) {
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

    @GetMapping("student")
    public String getStudentById(HttpSession session,
                                 @RequestParam(value = "method") String method,
                                 @RequestParam(value = "id") int id) {
        if ("getById".equals(method)) {
            Student student = studentService.findById(id).orElseThrow();
            int studentId = student.getId();
            String studentName = student.getName();
            String studentAddress = student.getAddress();
            session.setAttribute("id", studentId);
            session.setAttribute("name", studentName);
            session.setAttribute("address", studentAddress);
        } else return "error.jsp";

        return "student.jsp";
    }

    @GetMapping("studentform")
    public String showForm() {
        return "studentform.jsp";
    }

    @PostMapping("studentform")
    public String createStudent(Model model,
                                @Valid Student student,
                                Errors errors) {

            if (errors.hasErrors()) {
                FieldError nameError = errors.getFieldError("name");
                FieldError addressError = errors.getFieldError("address");
                model.addAttribute("nameError", nameError);
                model.addAttribute("addressError", addressError);
                return "studentform.jsp";
            }

        studentService.save(student);
        return "students.jsp";
    }

}








