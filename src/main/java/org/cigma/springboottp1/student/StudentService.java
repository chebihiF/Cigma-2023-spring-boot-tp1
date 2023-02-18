package org.cigma.springboottp1.student;

import java.util.List;

public interface StudentService {
    Student addStudent(Student student) throws Exception;
    Student updateStudent(Student student) throws Exception;
    Student deleteStudent(Student student) throws Exception;
    Student getStudent(Long id) throws Exception;
    List<Student> getStudents() throws Exception;
    Student getStudentsByEmail(String email) throws Exception;
}
