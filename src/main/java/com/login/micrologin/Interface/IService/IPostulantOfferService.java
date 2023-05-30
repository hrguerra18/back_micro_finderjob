package com.login.micrologin.Interface.IService;

import com.login.micrologin.Entity.Offers;
import com.login.micrologin.Entity.Postulant;
import com.login.micrologin.Entity.PostulantOffer;
import com.login.micrologin.Service.Offer.OfferResponse;
import com.login.micrologin.Service.PostulantOffer.PostulantOfferResponse;
import com.login.micrologin.Service.PostulantOffer.Request.PostulantOfferSave;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPostulantOfferService {
    PostulantOfferResponse save(PostulantOfferSave postulantOfferSave);
    PostulantOfferResponse findAllByOfferId(Long offertId);
    PostulantOffer findById(Long id);
    PostulantOfferResponse deletePostulantOfferByPostulant(Long offerId, Long postulantId);
    PostulantOfferResponse changeState(Long postulantOfferId, int stateId);

    PostulantOfferResponse finAllByStateAndPostulant(Long postulantId, int stateId);


}
