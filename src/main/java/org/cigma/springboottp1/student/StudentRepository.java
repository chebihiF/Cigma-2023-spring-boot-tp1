package org.cigma.springboottp1.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findStudentByEmail(String email);

    @Query("from Student s where s.email like %:mc% " +
            "and s.name like %:mc% ")
    List<Student> findStudentByQuery(String mc);
}
