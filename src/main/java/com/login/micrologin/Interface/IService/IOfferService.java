package com.login.micrologin.Interface.IService;

import com.login.micrologin.Entity.Offers;
import com.login.micrologin.Service.Offer.OfferResponse;

public interface IOfferService {
    OfferResponse save(Offers offer);
    OfferResponse deleteById(Long offerId);
    OfferResponse findAll();
    OfferResponse findAllByCompanyId(Long companyId);


}
