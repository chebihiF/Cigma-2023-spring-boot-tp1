package org.cigma.springboottp1.student;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class Student {
    @SequenceGenerator(
            name = "my_sequence",
            sequenceName = "my_sequence",
            allocationSize = 1
    )
    @Id @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "my_sequence"
    )
    private Long code ;
    private String name ;
    //@Column(unique = true)
    private String email ;
    private LocalDate dob ;
}
