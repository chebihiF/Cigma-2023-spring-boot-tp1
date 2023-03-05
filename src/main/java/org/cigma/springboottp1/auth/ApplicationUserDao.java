package org.cigma.springboottp1.auth;

import org.springframework.security.core.userdetails.UserDetails;

public interface ApplicationUserDao {
    ApplicationUserDetails getUserByUsername(String username);
}
