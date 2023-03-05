package org.cigma.springboottp1.auth;

import lombok.RequiredArgsConstructor;
import org.cigma.springboottp1.user.Authority;
import org.cigma.springboottp1.user.AuthorityRepository;
import org.cigma.springboottp1.user.User;
import org.cigma.springboottp1.user.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Repository("mysqlRepo")
@RequiredArgsConstructor
public class ApplicationUserRepositoryMysql implements ApplicationUserDao{

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    @Override
    public ApplicationUserDetails getUserByUsername(String username) {
        Optional<User> userFound = userRepository.findById(username);
        if(!userFound.isPresent())
            throw new RuntimeException("user not found");
        User user = userFound.get();
        List<Authority> authorities = authorityRepository.findAuthoritiesByUsersContains(user);
        return new ApplicationUserDetails(
                user.getPassword(),
                user.getEmail(),
                authorities.stream()
                        .map(authority -> new SimpleGrantedAuthority
                                (authority.getName())).collect(Collectors.toSet()),
                user.isAccountNonExpired(),
                user.isAccountNonLocked(),
                user.isCredentialsNonExpired(),
                user.isEnabled()
        );
    }
}
