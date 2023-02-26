package org.cigma.springboottp1.student;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    @Override
    public Student addStudent(Student student) throws Exception {
        // Metier
        if(student!=null)
            return studentRepository.save(student);
        else
            throw new RuntimeException("student cannot be added");
    }

    @Override
    public Student updateStudent(Student student) throws Exception {
        return studentRepository.save(student);
    }

    @Override
    public Student deleteStudent(Long id) throws Exception {
        Student student = getStudent(id);
        studentRepository.deleteById(id);
        return student ;
    }

    @Override
    public Student getStudent(Long id) throws Exception {
        if(studentRepository.findById(id).isPresent())
            return studentRepository.findById(id).get();
        throw new RuntimeException("student not found");
    }

    @Override
    public List<Student> getStudents() throws Exception {
        return studentRepository.findAll();
    }

    @Override
    public Page<Student> getStudentsAsPage(Pageable pageable) throws Exception {
        return studentRepository.findAll(pageable);
    }

    @Override
    public Student getStudentsByEmail(String email) throws Exception {
        List<Student> students = studentRepository.findStudentByEmail(email);
        if(students.size()>0)
            return students.get(0);
        throw new RuntimeException("student not found");
    }
    @Override
    public Page<Student> getStudentsByKeyword(String keyword, Pageable pageable) throws Exception {
        return studentRepository.findStudentsByNameContainingOrEmailContaining(keyword, keyword, pageable);
    }
}
