package com.login.micrologin.Service.Company;

import com.login.micrologin.Entity.Company;
import com.login.micrologin.Entity.Offers;
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
                x.setUserBE(null);
                x.setLstOffers(null);
            });
            response.lstCompany = lstCompany;
        }
        return response;
    }
    @Override
    public ServiceCompanyResponse findById(Long id) {
        Company company = findCompanyById(id);
        ServiceCompanyResponse response = new ServiceCompanyResponse();
        if (company != null){
            company.getUserBE().setCompany(null);
            if (!company.getLstOffers().isEmpty()){
               company.setLstOffers(null);
            }
            response.company = company;
            response.messagge = "Se ha encontrado la empresa";
        }else{
            response.messagge = "No existe una empresa con ese ID";
        }
        return response;
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
    public ServiceCompanyResponse update(Company companyRequest) {
        ServiceCompanyResponse response = new ServiceCompanyResponse();
        Company company = findCompanyById(companyRequest.getId());
        if (company != null){
            company = mapCompany(companyRequest, company);
            _companyRepository.save(company);
            company.setUserBE(null);
            company.setLstOffers(null);
            response.company = company;
        }else{
            response.messagge = "No se encontro la empresa que desea modificar.";
        }
        return response;
    }

    @Override
    public void delete(Long id) {
        _companyRepository.deleteById(id);
    }

    private Company findCompanyById(Long id){
        if (id > 0){
            Company companyResponse = new Company();
            Optional<Company> companyOptional = _companyRepository.findById(id);
            if (!companyOptional.isEmpty()){
                return companyOptional.get();
            }else{
                return null;
            }
        }
        return null;
    }

    private Company mapCompany(Company companyRequest, Company companyResponse){
        companyResponse.setName(companyRequest.getName());
        companyResponse.setDescription(companyRequest.getDescription());
        companyResponse.setNit(companyRequest.getNit());
        companyResponse.setAddress(companyRequest.getAddress());
        companyResponse.setPhone(companyRequest.getPhone());
        return companyResponse;
    }
}
