package org.cigma.springboottp1.student;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.websocket.server.PathParam;
import java.util.List;

@Controller
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public String index(
            Model model,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size){
        try {
            Page<Student> students = studentService.getStudentsAsPage(
                    PageRequest.of(page,size)
            );
            model.addAttribute("students", students);
            model.addAttribute("pages", new int[students.getTotalPages()]);
            model.addAttribute("currentPage", page);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error",e.getMessage());
        }
        return "students";
    }

}
