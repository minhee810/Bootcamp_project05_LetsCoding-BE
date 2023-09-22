package com.group.letscoding.config.auth;

import com.group.letscoding.domain.user.User;
import com.group.letscoding.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = userRepository.findByUsername(username);
        if (userEntity == null) {
<<<<<<< HEAD
            throw new UsernameNotFoundException("User not found with username: " + username);
=======
            return null;
>>>>>>> c4fc5dfe42d9d6dfaa96c68fcbb04aa4a546e6f4
        } else {
            return new PrincipalDetails(userEntity);
        }
    }
}
