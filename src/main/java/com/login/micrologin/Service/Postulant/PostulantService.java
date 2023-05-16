package com.login.micrologin.Service.Postulant;

import com.login.micrologin.Entity.*;
import com.login.micrologin.Entity.Enum.UserType;
import com.login.micrologin.Interface.IService.IPostulantService;
import com.login.micrologin.Repository.PostulantRepository;
import com.login.micrologin.Service.User.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostulantService implements IPostulantService {
    private final PostulantRepository _postulantRepository;
    private UserService _userService;

    public PostulantService(PostulantRepository postulantRepository, UserService userService){
        _postulantRepository = postulantRepository;
        _userService = userService;
    }


    @Override
    public PostulantReponse save(Postulant postulant) {
        PostulantReponse response = new PostulantReponse();
        if (postulant != null){
            User userRequest = postulant.getUserBE();
            if (userRequest != null){
                String email = userRequest.getEmail();
                User userExist = _userService.findUserByEmail(email);

                if(userExist != null){
                    response.messagge = "Error!! El email " + email + " ya se encuentra ocupado";
                    return response;
                }

                postulant.getUserBE().setUserType(UserType.Postulante.returnId());
                response.postulant = _postulantRepository.save(postulant);
            }else{
                response.messagge = "No vienen datos para el usuario";
            }
        }else{
            response.messagge = "No vienen datos para el postulante";
        }
        return response;
    }

    @Override
    public PostulantReponse update(Postulant postulant) {
        PostulantReponse response = new PostulantReponse();
        if (postulant.getId() > 0){
            Postulant PostulantFound = findById(postulant.getId()).postulant;
            if (PostulantFound != null){
                PostulantFound = mapPostulant(postulant, PostulantFound);
                _postulantRepository.save(PostulantFound);
                PostulantFound.setUserBE(null);
                response.postulant = PostulantFound;
            }else{
                response.messagge = "El aspirante que desea modificar no existe!!";
            }
        }else{
            response.messagge = "El id del aspirante no puede ser Cero";
        }

        return response;
    }

    @Override
    public PostulantReponse findById(Long id) {
        PostulantReponse response = new PostulantReponse();
        if (id > 0){
            Optional<Postulant> optionalPostulant = _postulantRepository.findById(id);
            if (!optionalPostulant.isEmpty()){
                optionalPostulant.get().getUserBE().setPostulant(null);
                response.postulant = optionalPostulant.get();
                response.messagge = "Se ha encontrado el aspirante";
            }else{
                response.messagge = "El aspirante no existe";
            }
        }else{
            response.messagge = "El id tiene que ser mayor a CERO!!";
        }

        return response;
    }

    @Override
    public List<PostulantOffer> getAllOffersByPostulate(Long postulateId) {
        List<PostulantOffer> lstPostulantOffers = new ArrayList<PostulantOffer>();
        if (postulateId > 0){
            Optional<Postulant> optionalPostulant = _postulantRepository.findById(postulateId);
            if (!optionalPostulant.isEmpty()){
                optionalPostulant.get().setUserBE(null);
                if (optionalPostulant.get().getLstPostulantOffer() != null && optionalPostulant.get().getLstPostulantOffer().size() > 0){
                    optionalPostulant.get().getLstPostulantOffer().forEach(x -> {
                        x.setPostulant(null);
                        x.getOffert().setCompany(null);
                        x.getOffert().setLstPostulantOffer(null);
                    });
                }
                lstPostulantOffers = optionalPostulant.get().getLstPostulantOffer();
                return lstPostulantOffers;
            }
        }
        return lstPostulantOffers;
    }

    private Postulant mapPostulant(Postulant postulantRequest, Postulant postulantResponse){
        postulantResponse.setFirstName(postulantRequest.getFirstName());
        postulantResponse.setSecondName(postulantRequest.getSecondName());
        postulantResponse.setFirstLastName(postulantRequest.getFirstLastName());
        postulantResponse.setSecondLastName(postulantRequest.getSecondLastName());
        postulantResponse.setJob(postulantRequest.getJob());
        postulantResponse.setAddress(postulantRequest.getAddress());
        postulantResponse.setPhone(postulantRequest.getPhone());
        postulantResponse.setAge(postulantRequest.getAge());
        return postulantResponse;
    }
}
