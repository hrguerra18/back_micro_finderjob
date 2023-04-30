package com.login.micrologin.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "COMPANY")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COM_COMPANY_ID")
    private Long Id;
    @Column(name = "COM_NAME")
    private String Name;
    @Column(name = "COM_DESCRIPTION")

    private String Description;
    @Column(name = "COM_NIT")

    private String Nit;
    @Column(name = "COM_ADDRESS")

    private String Address;
    @Column(name = "COM_PHONE")

    private String Phone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "COM_USER_ID")
    private User userBE;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Offers> lstOffers;




}

