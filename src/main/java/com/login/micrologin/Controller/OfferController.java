package com.login.micrologin.Controller;

import com.login.micrologin.Entity.Offers;
import com.login.micrologin.Interface.IService.IOfferService;
import com.login.micrologin.Service.Offer.OfferResponse;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/{id}")
    public OfferResponse findAllByCompanyId(@PathVariable("id") Long CompanyId){
        return _offerService.findAllByCompanyId(CompanyId);
    }
}
