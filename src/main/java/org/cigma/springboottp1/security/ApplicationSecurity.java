package org.cigma.springboottp1.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {


    private final PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("index.html").permitAll()
                .antMatchers(HttpMethod.POST,"/api/v1/**").hasAuthority("student:write")
                .antMatchers("/api/v1/**").hasAnyRole("ADMIN","USER")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .authorities("student:read","student:write","ROLE_ADMIN")
                .build();


        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder.encode("user"))
                .authorities("student:read","ROLE_USER")
                .build();


        return new InMemoryUserDetailsManager(
                user, admin
        );
    }
}
