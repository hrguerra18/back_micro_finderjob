package com.login.micrologin.Controller;

import com.login.micrologin.Entity.Offers;
import com.login.micrologin.Entity.Postulant;
import com.login.micrologin.Interface.IService.IOfferService;
import com.login.micrologin.Service.Offer.OfferResponse;
import com.login.micrologin.Service.Offer.Request.ChangeStateOffert;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/offer")
public class OfferController {
    private final IOfferService _offerService;

    public OfferController(IOfferService _offerService) {
        this._offerService = _offerService;
    }

    @PostMapping
    public OfferResponse save(@RequestBody Offers offer){
        return _offerService.save(offer);
    }

    @DeleteMapping("/{id}")
    public OfferResponse deleteById(@PathVariable("id") Long id){
        return _offerService.deleteById(id);
    }

    @GetMapping
    public OfferResponse findAll(){
        return _offerService.findAll();
    }
    @GetMapping("/findAllByCompanyId/{id}")
    public OfferResponse findAllByCompanyId(@PathVariable("id") Long CompanyId){
        return _offerService.findAllByCompanyId(CompanyId);
    }

    @PostMapping("/changeState")
    public OfferResponse changeState(@RequestBody ChangeStateOffert changeStateOffert){
        return _offerService.changeState(changeStateOffert);
    }

    @GetMapping("/{id}")
    public OfferResponse findById(@PathVariable("id") Long id){
        return _offerService.findById(id);
    }

    @GetMapping("/postulateByOffer/{id}")
    public List<Postulant> postulateByOffer(@PathVariable("id") Long id){
        return _offerService.postulateByOffer(id);
    }

    @GetMapping("/offertActive")
    public OfferResponse findAllActive(){
        return _offerService.findAllActive();
    }
}
