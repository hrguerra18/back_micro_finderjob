package com.login.micrologin.Service.PostulantOffer.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostulantOfferSave {
    private Long idOffer;
    private Long idPostulant;
}
