package com.login.micrologin.Service.PostulantOffer;

import com.login.micrologin.Entity.Enum.PostulantOfferState;
import com.login.micrologin.Entity.Offers;
import com.login.micrologin.Entity.Postulant;
import com.login.micrologin.Entity.PostulantOffer;
import com.login.micrologin.Interface.IService.IPostulantOfferService;
import com.login.micrologin.Repository.OfferRepository;
import com.login.micrologin.Repository.PostulantOfferRepository;
import com.login.micrologin.Repository.PostulantRepository;
import com.login.micrologin.Service.Offer.OfferResponse;
import com.login.micrologin.Service.Offer.OfferService;
import com.login.micrologin.Service.Postulant.PostulantReponse;
import com.login.micrologin.Service.Postulant.PostulantService;
import com.login.micrologin.Service.PostulantOffer.Request.PostulantOfferSave;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class PostulantOfferService implements IPostulantOfferService {
    private final PostulantOfferRepository _postulantOffer;
    private final OfferRepository _offerRepository;
    private final PostulantRepository _postulantRepository;
    public PostulantOfferService(PostulantOfferRepository postulantOffer,OfferRepository _offerRepository, PostulantRepository _postulantRepository){
        _postulantOffer = postulantOffer;
        this._offerRepository = _offerRepository;
        this._postulantRepository = _postulantRepository;
    }
    @Override
    public PostulantOfferResponse save(PostulantOfferSave postulantOfferSave) {
        PostulantOfferResponse response = new PostulantOfferResponse();
        if (postulantOfferSave != null && postulantOfferSave.getIdOffer() > 0){
            Optional<Offers> offerResponse = _offerRepository.findById(postulantOfferSave.getIdOffer());
            if (offerResponse != null  && !offerResponse.isEmpty()){
                List<PostulantOffer> lstPostulantOffert = _postulantOffer.findAllByOfferId(postulantOfferSave.getIdOffer());
                AtomicBoolean found = new AtomicBoolean(false);
                if (lstPostulantOffert != null && lstPostulantOffert.size() > 0){
                    lstPostulantOffert.forEach(x -> {
                        Postulant postulant = x.getPostulant();
                        if ( postulant != null){
                            if (postulant.getId() == postulantOfferSave.getIdPostulant()){
                                found.set(true);
                            }
                        }
                    });
                }
                if (!found.get()){
                    offerResponse.get().setLstPostulantOffer(null);
                    if (lstPostulantOffert.size() < offerResponse.get().getAmountApplicants()){
                        Offers offer = new Offers();
                        offer.setId(postulantOfferSave.getIdOffer());
                        Postulant postulant = new Postulant();
                        postulant.setId(postulantOfferSave.getIdPostulant());
                        offer.setCompany(offerResponse.get().getCompany());
                        PostulantOffer postulantOffer = new PostulantOffer();
                        postulantOffer.setPostulant(postulant);
                        postulantOffer.setOffert(offer);
                        postulantOffer.setState(PostulantOfferState.ONHOLD.returnPostulantOffertStateId());
                        response.postulantOffer =  _postulantOffer.save(postulantOffer);
                        response.postulantOffer.setPostulant(null);
                        response.postulantOffer.setOffert(null);
                        response.messagge = "Se guardo correctamente la postulacion";
                    }else{
                        response.messagge = "Esta oferta ya tiene el limite de aplicantes";
                    }
                }else{
                    response.messagge = "Ya te encuentras postulado a esta oferta!!";
                }

            }else{
                response.messagge = "No se encontro esta oferta";
            }
        }else{
            response.messagge = "El id de la oferta tiene que ser mayor a cero!";
        }
        return response;
    }

    @Override
    public PostulantOfferResponse findAllByOfferId(Long offertId) {
        PostulantOfferResponse response = new PostulantOfferResponse();
        List<PostulantOffer> lstPostulantOffert = _postulantOffer.findAllByOfferId(offertId);
        if (lstPostulantOffert != null && lstPostulantOffert.size() > 0){
            PostulantOffer postulantOffer = findById(lstPostulantOffert.stream().findFirst().get().getId());
            response.lstPostulantOffer = lstPostulantOffert;
        }else{
            response.messagge = "Esta oferta no tiene postulantes";
        }

        return response;
    }

    @Override
    public PostulantOffer findById(Long id) {
        PostulantOfferResponse response = new PostulantOfferResponse();
        Optional<PostulantOffer> optionalPostulantOffer = _postulantOffer.findById(id);
        if (optionalPostulantOffer != null && !optionalPostulantOffer.isEmpty()){
             response.postulantOffer = optionalPostulantOffer.get();
            response.postulantOffer.getPostulant().setLstPostulantOffer(null);
            response.postulantOffer.getPostulant().setUserBE(null);
            response.postulantOffer.getOffert().setLstPostulantOffer(null);
            optionalPostulantOffer.get().getPostulant().setLstPostulantOffer(null);
            optionalPostulantOffer.get().getPostulant().setUserBE(null);
            optionalPostulantOffer.get().getOffert().setLstPostulantOffer(null);
            optionalPostulantOffer.get().getOffert().setCompany(null);
             return optionalPostulantOffer.get();
        }else{
            return null;
        }
    }

    @Override
    public PostulantOfferResponse deletePostulantOfferByPostulant(Long offerId, Long postulantId) {
        PostulantOfferResponse response = new PostulantOfferResponse();
        Optional<Offers> offers = _offerRepository.findById(offerId);
        if (offers != null && !offers.isEmpty()){
            Optional<Postulant> postulant = _postulantRepository.findById(postulantId);
            if (postulant != null && !postulant.isEmpty()){
                _postulantOffer.deletePostulantOfferByPostulant(offerId, postulantId);
                response.messagge = "La postulacion a la oferta a sido eliminada correctamente";
            }else{
                response.messagge = "El aspirante no existe";
            }
        }else{
            response.messagge = "La oferta no existe";
        }
        return response;
    }


}
