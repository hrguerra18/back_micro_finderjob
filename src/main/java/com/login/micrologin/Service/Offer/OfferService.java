package com.login.micrologin.Service.Offer;

import com.login.micrologin.Entity.Company;
import com.login.micrologin.Entity.Enum.OfferState;
import com.login.micrologin.Entity.Offers;
import com.login.micrologin.Entity.Postulant;
import com.login.micrologin.Entity.User;
import com.login.micrologin.Interface.IService.IOfferService;
import com.login.micrologin.Repository.OfferRepository;
import com.login.micrologin.Service.Offer.Request.ChangeStateOffert;
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
        response.offer.setLstPostulantOffer(null);
        response.offer.setCompany(null);
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
                x.setLstPostulantOffer(null);
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
                    x.setLstPostulantOffer(null);
                }
            });
            response.lstOffer = lstOffer;
        }
        return response;
    }

    @Override
    public OfferResponse changeState(ChangeStateOffert changeStateOffert){
        OfferResponse response = new OfferResponse();
        try {
            if (changeStateOffert != null && changeStateOffert.getIdOffer() > 0 && changeStateOffert.getIdState() > 0){
                Optional<Offers> offers = _offerRepository.findById(changeStateOffert.getIdOffer());
                if (!offers.isEmpty()){
                    offers.get().getCompany().setLstOffers(null);
                    offers.get().getCompany().getUserBE().setCompany(null);
                    boolean exist = false;
                    for (OfferState item : OfferState.values()){
                        if (item.returnOfferStateId() == changeStateOffert.getIdState()){
                            exist = true;
                        }
                    }
                    if (exist){
                        offers.get().setState(changeStateOffert.getIdState());
                        _offerRepository.save(offers.get());
                        response.messagge = "El estado de la oferta a sido actualizada";
                        offers.get().setCompany(null);
                        offers.get().setLstPostulantOffer(null);
                        response.offer = offers.get();
                    }else{
                        response.messagge = "El estado enviado no existe";
                    }
                }else{
                    response.messagge = "La oferta enviada al no existe";
                }
            }
            return response;
        }catch (Exception e){
            response.messagge = e.getMessage();
            return response;
        }
    }

    @Override
    public OfferResponse findById(Long offerId) {
        OfferResponse response = new OfferResponse();
        Optional<Offers> optionalOffer = _offerRepository.findById(offerId);
        if (!optionalOffer.isEmpty()){
            Offers offerResult = new Offers();
            offerResult = optionalOffer.get();
            if (offerResult != null){
                offerResult.setCompany(null);
                offerResult.setLstPostulantOffer(null);
                response.offer = offerResult;
            }else{
                response.messagge = "No se encontro ninguna oferta";
            }
        }else{
            response.messagge = "No se encontro ninguna oferta";
        }

        return response;
    }

    @Override
    public List<Postulant> postulateByOffer(Long offerId) {
        Optional<Offers> optional = _offerRepository.findById(offerId);
        List<Postulant> postulantList = new ArrayList<Postulant>();
        if(optional != null && !optional.isEmpty()){
            if(optional.get().getLstPostulantOffer() != null && optional.get().getLstPostulantOffer().size() > 0){
                optional.get().getLstPostulantOffer().forEach(x -> {
                    x.getPostulant().setLstPostulantOffer(null);
                    x.getPostulant().setUserBE(null);
                    x.getOffert().setLstPostulantOffer(null);
                    postulantList.add(x.getPostulant());
                });
            }
        }
        return postulantList;
    }



    @Override
    public OfferResponse findAllActive() {
        OfferResponse response = new OfferResponse();
        int stateId = 1;
        List<Offers> lstOffer = _offerRepository.findAllByState(stateId);
        if (lstOffer != null && lstOffer.size() > 0){
            lstOffer.forEach(x -> {
                x.setLstPostulantOffer(null);
                x.setCompany(null);
            });
            response.lstOffer = lstOffer;
            response.messagge = "Estas son las ofertas activas";
        }else{
            response.messagge = "No hay ofertas activas";
        }
        return response;
    }


}
