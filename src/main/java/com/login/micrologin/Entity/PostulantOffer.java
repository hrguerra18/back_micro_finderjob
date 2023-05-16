package com.login.micrologin.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "postulant_offert")
public class PostulantOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POF_POSTULANT_OFFERT_ID")
    private Long Id;
    @Column(name = "POF_STATE")
    private int state;
    @ManyToOne()
    @JoinColumn(name = "POF_POSTULANT_ID")
    private Postulant postulant;
    @ManyToOne()
    @JoinColumn(name = "POF_OFFERT_ID")
    private Offers offert;


}
