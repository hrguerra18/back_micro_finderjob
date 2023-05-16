package com.login.micrologin.Interface.IService;

import com.login.micrologin.Entity.Company;
import com.login.micrologin.Entity.Offers;
import com.login.micrologin.Entity.Postulant;
import com.login.micrologin.Service.Offer.OfferResponse;
import com.login.micrologin.Service.Offer.Request.ChangeStateOffert;

import java.util.List;
import java.util.Optional;

public interface IOfferService {
    OfferResponse save(Offers offer);
    OfferResponse deleteById(Long offerId);
    OfferResponse findAll();
    OfferResponse findAllByCompanyId(Long companyId);
    OfferResponse changeState(ChangeStateOffert changeStateOffert);

    OfferResponse findById(Long offerId);
    List<Postulant> postulateByOffer(Long offerId);

    OfferResponse findAllActive();





}
