package org.cigma.springboottp1.student;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {
    Student addStudent(Student student) throws Exception;
    Student updateStudent(Student student) throws Exception;
    Student deleteStudent(Long id) throws Exception;
    Student getStudent(Long id) throws Exception;
    List<Student> getStudents() throws Exception;
    Page<Student> getStudentsAsPage (Pageable pageable) throws Exception;
    Student getStudentsByEmail(String email) throws Exception;

    Page<Student> getStudentsByKeyword(String keyword, Pageable pageable) throws Exception;
}
