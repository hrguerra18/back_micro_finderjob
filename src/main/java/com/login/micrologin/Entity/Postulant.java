package com.login.micrologin.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "POSTULANT")
@Entity
public class Postulant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POS_POSTULANT_ID")
    private Long id;
    @Column(name = "POS_FIRST_NAME")
    private String firstName;
    @Column(name = "POS_SECOND_NAME")
    private String secondName;
    @Column(name = "POS_FIRST_LASTNAME")
    private String firstLastName;
    @Column(name = "POS_SECOND_LASTNAME")
    private String secondLastName;
    @Column(name = "POS_JOB")
    private String job;
    @Column(name = "POS_IDENTIFICATION")
    private String identification;
    @Column(name = "POS_ADDRESS")
    private String address;
    @Column(name = "POS_PHONE")
    private String phone;
    @Column(name = "POS_GENDER")
    private String gender;
    @Column(name = "POS_AGE")
    private int age;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "POS_USER_ID")
    private User userBE;
    @OneToMany(mappedBy = "postulant")
    private List<PostulantOffer> lstPostulantOffer;

}
