package com.login.micrologin.Service.Offer.Request;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ChangeStateOffert {
    private int idOffert;
    private int idState;
}
