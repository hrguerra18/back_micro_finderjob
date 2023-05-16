package com.login.micrologin.Interface.IService;

import com.login.micrologin.Entity.Offers;
import com.login.micrologin.Entity.Postulant;
import com.login.micrologin.Entity.PostulantOffer;
import com.login.micrologin.Service.Company.ServiceCompanyResponse;
import com.login.micrologin.Service.Postulant.PostulantReponse;

import java.util.List;

public interface IPostulantService {
    PostulantReponse save(Postulant postulant);
    PostulantReponse update(Postulant postulant);
    PostulantReponse findById(Long id);

    List<PostulantOffer> getAllOffersByPostulate(Long postulateId);

}
