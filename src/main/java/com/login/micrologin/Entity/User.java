package com.login.micrologin.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USERS")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USE_USER_ID")
    private Long id;
    @Column(name = "USE_EMAIL")

    private String email;
    @Column(name = "USE_PASSWORD")

    private String password;
    @OneToOne(mappedBy = "userBE")
    private Company company;

    @Column(name = "USE_USER_TYPE")
    private int userType;

    @OneToOne(mappedBy = "userBE")
    private Postulant postulant;

}
