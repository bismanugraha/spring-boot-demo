package com.spring.demo.config;

import com.spring.demo.enums.RoleEnum;
import com.spring.demo.model.UserEntity;
import com.spring.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                UserEntity admin = new UserEntity(
                    "admin",
                    passwordEncoder.encode("admin123"),
                    RoleEnum.ADMIN
                );
                userRepository.save(admin);
                System.out.println("Admin user created with username: admin, password: admin123");
            }
        };
    }
}