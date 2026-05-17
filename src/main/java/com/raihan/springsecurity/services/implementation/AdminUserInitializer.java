package com.raihan.springsecurity.services.implementation;

import com.raihan.springsecurity.entity.Users;
import com.raihan.springsecurity.enums.Role;
import com.raihan.springsecurity.repository.UsersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author Raihan-89
 */
@Component
public class AdminUserInitializer {

    @Bean
    public CommandLineRunner createAdminUser(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (usersRepository.findByUsername("admin").isEmpty()) {
                Users admin = new Users();
                admin.setEmail("admin@gmail.com");
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole(Role.ADMIN);

                usersRepository.save(admin);
            }

            if (usersRepository.findByUsername("user").isEmpty()) {
                Users user = new Users();
                user.setEmail("user@gmail.com");
                user.setUsername("user");
                user.setPassword(passwordEncoder.encode("user123"));
                user.setRole(Role.USER);

                usersRepository.save(user);
            }
        };
    }
}
