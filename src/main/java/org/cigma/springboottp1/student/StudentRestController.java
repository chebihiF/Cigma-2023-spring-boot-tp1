package org.cigma.springboottp1.student;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
@RequiredArgsConstructor
public class StudentRestController {
    private final StudentService studentService;

    @DeleteMapping("/{id}")
    public Student deleteStudent(@PathVariable(name = "id") Long id){
        try {
            return studentService.deleteStudent(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{email}")
    public Student getByEmail(@PathVariable(name = "email") String email){
        try {
            return studentService.getStudentsByEmail(email);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

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
