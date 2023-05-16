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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "OFFERS")
public class Offers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OFE_OFFERS_ID")
    private Long id;
    @Column(name = "OFE_JOB")

    private String job;
    @Column(name = "OFE_EXPIRATION_DATE")

    private Date expirationDate;
    @Column(name = "OFE_AMOUNT_APPLICANTS")

    private int amountApplicants;
    @Column(name = "OFE_DESCRIPTION")

    private String description;
    @Column(name = "OFE_SALARY")

    private Double salary;
    @Column(name = "OFE_CONDITIONS")

    private String conditions;
    @Column(name = "OFE_SCHEDULE")

    private String schedule;
    @Column(name = "OFE_APPLICANT_PROFILE")

    private String applicantProfile;
    @Column(name = "OFE_STATE")

    private int state;
    @ManyToOne()
    @JoinColumn(name = "OFE_COMPANY_ID")
    private Company company;
    @OneToMany(mappedBy = "offert")
    private List<PostulantOffer> lstPostulantOffer;



}
