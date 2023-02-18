package org.cigma.springboottp1.student;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
@RequiredArgsConstructor
public class StudentConfig {
    private final StudentRepository studentRepository;

    @Bean
    CommandLineRunner initStudent(){
        return args -> {
          studentRepository.save
                  (new Student(
                          null,
                          "chebihi",
                          "f.chebihi@gmail.com",
                          LocalDate.of(1987,3,14)));
        };
    }
}
