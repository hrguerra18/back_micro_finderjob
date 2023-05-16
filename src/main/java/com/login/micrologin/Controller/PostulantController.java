package com.login.micrologin.Controller;

import com.login.micrologin.Entity.Company;
import com.login.micrologin.Entity.Offers;
import com.login.micrologin.Entity.Postulant;
import com.login.micrologin.Entity.PostulantOffer;
import com.login.micrologin.Interface.IService.IPostulantService;
import com.login.micrologin.Service.Company.ServiceCompanyResponse;
import com.login.micrologin.Service.Postulant.PostulantReponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/postulant")
public class PostulantController {
    private final IPostulantService _postulantService;
    public PostulantController(IPostulantService postulantService){
        _postulantService = postulantService;
    }
    @PostMapping
    public PostulantReponse save(@RequestBody Postulant postulant) {
        return _postulantService.save(postulant);
    }
    @PutMapping
    public PostulantReponse update(@RequestBody Postulant postulant){return _postulantService.update(postulant);}

    @GetMapping("/{id}")
    public PostulantReponse findById(@PathVariable("id") Long id){
        return _postulantService.findById(id);
    }
    @GetMapping("/getAllOffersByPostulate/{id}")
    public List<PostulantOffer> getAllOffersByPostulate(@PathVariable("id") Long id){
        return _postulantService.getAllOffersByPostulate(id);
    }
}
