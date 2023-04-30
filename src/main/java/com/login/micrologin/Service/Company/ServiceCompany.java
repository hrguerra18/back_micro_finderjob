package com.login.micrologin.Service.Company;

import com.login.micrologin.Entity.Company;
import com.login.micrologin.Entity.User;
import com.login.micrologin.Entity.Enum.UserType;
import com.login.micrologin.Interface.IService.ICompanyService;
import com.login.micrologin.Repository.CompanyRepository;
import com.login.micrologin.Service.User.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceCompany implements ICompanyService {
    private CompanyRepository _companyRepository;
    private UserService _userService;


    public ServiceCompany(CompanyRepository companyRepository, UserService userService){
        _companyRepository = companyRepository;
        _userService = userService;
    }

    @Override
    public ServiceCompanyResponse getAll() {
        ServiceCompanyResponse response = new ServiceCompanyResponse();
        List<Company> lstCompany = _companyRepository.findAll();
        if (lstCompany.isEmpty()) {
            response.messagge = "No se encuentra registros de empresas!!";
        } else {
            lstCompany.forEach(x -> {
                User user = x.getUserBE();
                if ( user != null){
                    user.setCompany(null);
                }
            });
            response.lstCompany = lstCompany;
        }
        return response;
    }
    @Override
    public Optional<Company> findById(Long id) {
        return _companyRepository.findById(id);
    }

    @Override
    public ServiceCompanyResponse save(Company company) {
        ServiceCompanyResponse response = new ServiceCompanyResponse();
        if (company != null){
            User userRequest = company.getUserBE();
            if (userRequest != null){
                String email = userRequest.getEmail();
                User userExist = _userService.findUserByEmail(email);

                if(userExist != null){
                    response.messagge = "Error!! El email " + email + " ya se encuentra ocupado";
                    return response;
                }
                company.getUserBE().setUserType(UserType.Company.returnId());
                response.company = _companyRepository.save(company);
            }else{
                response.messagge = "No vienen datos para el usuario";
            }
        }else{
            response.messagge = "No vienen datos para la compañia";
        }
        return response;
    }

    @Override
    public Company update(Company company) {
        return _companyRepository.save(company);
    }

    @Override
    public void delete(Long id) {
        _companyRepository.deleteById(id);
    }
}
