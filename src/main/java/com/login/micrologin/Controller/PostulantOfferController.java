package com.login.micrologin.Controller;

import com.login.micrologin.Entity.PostulantOffer;
import com.login.micrologin.Interface.IService.IPostulantOfferService;
import com.login.micrologin.Interface.IService.IPostulantService;
import com.login.micrologin.Service.Offer.OfferResponse;
import com.login.micrologin.Service.PostulantOffer.PostulantOfferResponse;
import com.login.micrologin.Service.PostulantOffer.Request.PostulantOfferDeletePostulantOfferByPostulant;
import com.login.micrologin.Service.PostulantOffer.Request.PostulantOfferSave;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/postulantOffert")
public class PostulantOfferController {
    private final IPostulantOfferService _postulantOfferService;
    public PostulantOfferController(IPostulantOfferService _postulantOfferService){
        this._postulantOfferService = _postulantOfferService;
    }

    @PostMapping
    public PostulantOfferResponse save(@RequestBody PostulantOfferSave postulantOfferSave){
        return _postulantOfferService.save(postulantOfferSave);
    }

    @GetMapping("/findAllByOfferId/{id}")
    public PostulantOfferResponse findAllByOfferId(@PathVariable("id") Long id){
        return _postulantOfferService.findAllByOfferId(id);
    }

    @GetMapping("/{id}")
    public PostulantOffer findById(@PathVariable("id") Long id){
        return _postulantOfferService.findById(id);
    }

    @PostMapping("/deletePostulantOfferByPostulant")
    public PostulantOfferResponse deletePostulantOfferByPostulant(@RequestBody PostulantOfferDeletePostulantOfferByPostulant request) {
        return _postulantOfferService.deletePostulantOfferByPostulant(request.offerId, request.postulantId);
    }

}
