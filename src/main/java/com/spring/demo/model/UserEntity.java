package com.spring.demo.model;

import com.spring.demo.enums.RoleEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @Enumerated(jakarta.persistence.EnumType.STRING)
    private RoleEnum role;

    public UserEntity() {
    }

    public UserEntity(String username, String password, RoleEnum role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}