package service;

import domain.CateringUser;
import repository.CateringUserRepository;

import javax.inject.Inject;
import java.util.List;

public class CateringUserService {

    @Inject
    private CateringUserRepository cateringUserRepository;

    private List<CateringUser> findAllUser(){
        return cateringUserRepository.findAllUser();
    }
    
}
