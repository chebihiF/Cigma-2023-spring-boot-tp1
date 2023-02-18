package org.cigma.springboottp1.student;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
@RequiredArgsConstructor
public class StudentRestController {
    private final StudentService studentService;

    @PostMapping
    public Student addStudent(@RequestBody Student student){
        try {
            return studentService.addStudent(student);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public List<Student> getAll(){
        try {
            return studentService.getStudents();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
