package com.login.micrologin.Controller;

import com.login.micrologin.Entity.Company;
import com.login.micrologin.Interface.IService.ICompanyService;
import com.login.micrologin.Service.Company.ServiceCompanyResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/company")
public class CompanyController {
    private final ICompanyService companyService;

    public CompanyController(ICompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ServiceCompanyResponse getAll(){
        return companyService.getAll();
    }
    @GetMapping("/{id}")
    public ServiceCompanyResponse findById(@PathVariable("id") Long id) {
        return companyService.findById(id);
    }
    @PostMapping
    public ServiceCompanyResponse save(@RequestBody Company company) {
        return companyService.save(company);
    }

    @PutMapping
    public ServiceCompanyResponse update(@RequestBody Company company) {
        return companyService.update(company);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        companyService.delete(id);
    }


}
