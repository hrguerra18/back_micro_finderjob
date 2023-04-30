package com.login.micrologin.Service.Offer;

import com.login.micrologin.Entity.Company;
import com.login.micrologin.Entity.Enum.OfferState;
import com.login.micrologin.Entity.Offers;
import com.login.micrologin.Entity.User;
import com.login.micrologin.Interface.IService.IOfferService;
import com.login.micrologin.Repository.OfferRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OfferService implements IOfferService {
    private final OfferRepository _offerRepository;

    public OfferService(OfferRepository _offerRepository) {
        this._offerRepository = _offerRepository;
    }

    @Override
    public OfferResponse save(Offers offer) {
        OfferResponse response = new OfferResponse();
        offer.setState(OfferState.ACTIVE.returnOfferStateId());
        response.offer = _offerRepository.save(offer);

        return response;
    }

    @Override
    public OfferResponse deleteById(Long offerId) {
        OfferResponse response = new OfferResponse();
        Optional<Offers> offer = _offerRepository.findById(offerId);
        if (!offer.isEmpty()){
            _offerRepository.deleteById(offerId);
            response.offer = new Offers();
            response.offer.setId(offerId);
            response.messagge = "Se ha eliminado correctamente la oferta";
        }else{
            response.messagge = "No se ha encontrado la oferta que desea eliminar";
        }
        return response;
    }

    @Override
    public OfferResponse findAll() {
        OfferResponse response = new OfferResponse();
        List<Offers> lstOffer = _offerRepository.findAll();
        if (lstOffer.isEmpty()){
            response.messagge = "No hay ofertas creadas";
        }else{
            lstOffer.forEach(x -> {
                Company company = x.getCompany();
                if (company != null){
                    x.setCompany(null);
                }
            });
            response.lstOffer = lstOffer;
        }

        return response;
    }

    @Override
    public OfferResponse findAllByCompanyId(Long companyId) {
        OfferResponse response = new OfferResponse();
        List<Offers> lstOffer = _offerRepository.findAllByCompanyId(companyId);
        if (lstOffer.isEmpty()){
            response.messagge = "La empresa no tiene ofertas creadas.";
        }else{
            lstOffer.forEach(x -> {
                Company company = x.getCompany();
                if (company != null){
                    x.setCompany(null);
                }
            });
            response.lstOffer = lstOffer;
        }
        return response;
    }
}
