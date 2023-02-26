package org.cigma.springboottp1.student;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "keyword", defaultValue = "") String keyword
    ){
        try {
            Page<Student> students = studentService.getStudentsByKeyword(
                    keyword,PageRequest.of(page,size)
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



    @PostMapping
    public String search(@RequestBody String keyword, Model model){
        try {
            Page<Student> students = studentService
                    .getStudentsByKeyword(keyword, PageRequest.of(0, 5));
            model.addAttribute("students", students);
            model.addAttribute("pages", new int[students.getTotalPages()]);
            model.addAttribute("currentPage", 0);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error",e.getMessage());
        }

        return "students";
    }

}
