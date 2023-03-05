package org.cigma.springboottp1.auth;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository("memoryRepo")
public class ApplicationUserRepositoryInMemory implements ApplicationUserDao
{
    private List<ApplicationUserDetails> users ;
    private PasswordEncoder passwordEncoder;

    public ApplicationUserRepositoryInMemory(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        users = List.of(
                 new ApplicationUserDetails(
                         passwordEncoder.encode("admin"),
                         "admin",
                         Set.of("student:read","student:write","ROLE_ADMIN")
                                 .stream()
                                 .map(SimpleGrantedAuthority::new)
                                 .collect(Collectors.toSet())
                         ,
                         true,
                         true,
                         true,
                         true

                 ),
                new ApplicationUserDetails(
                        passwordEncoder.encode("user"),
                        "user",
                        Set.of("student:read","ROLE_USER")
                                .stream()
                                .map(SimpleGrantedAuthority::new)
                                .collect(Collectors.toSet())
                        ,
                        true,
                        true,
                        true,
                        true

                )
        );
    }

    @Override
    public ApplicationUserDetails getUserByUsername(String username) {
        return users
                .stream()
                .filter(user->user.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("user not found"));
    }
}
