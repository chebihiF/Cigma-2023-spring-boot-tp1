package org.cigma.springboottp1.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Authority {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name ;
    @ManyToMany
    @JoinTable(
            name = "role",
            joinColumns = @JoinColumn(name = "id_authority"),
            inverseJoinColumns = @JoinColumn(name = "email")
    )
    private List<User> users;
}
