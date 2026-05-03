package com.raihan.springsecurity.services.implementation;

import com.raihan.springsecurity.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Raihan-89
 */
@Service
@RequiredArgsConstructor
public class UsersDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found with the provided username")
        );
    }
}
