package com.bjb.pockit.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_authentications")
@Data
public class UserAuthenticationWILLREMOVE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_profile_id", referencedColumnName = "id")
    private UserProfile userProfiles;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "pin")
    private String pin;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
