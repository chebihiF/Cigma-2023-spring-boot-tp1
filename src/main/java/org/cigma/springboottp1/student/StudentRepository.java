package org.cigma.springboottp1.student;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("from Student s where s.name like %:keyword% or s.email like %:keyword%")
    Page<Student> findStudentByKeyword(String keyword, Pageable pageable);
    Page<Student> findStudentsByNameContainingOrEmailContaining(String name, String email, Pageable pageable);
    List<Student> findStudentByEmail(String email);

    @Query("from Student s where s.email like %:mc% " +
            "and s.name like %:mc% ")
    List<Student> findStudentByQuery(String mc);
}
