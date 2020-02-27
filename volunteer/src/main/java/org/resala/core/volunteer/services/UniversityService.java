package org.resala.core.volunteer.services;

import org.resala.core.volunteer.entities.UniversityEntitiy;
import org.resala.core.volunteer.repository.UniversityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@SuppressWarnings("ALL")
@Service
public class UniversityService {

    private final Object UniversityRepository;
    private UniversityRepository universityRepository;

    public static void getUniversity() {
    }

      UniversityService(final UniversityRepository xUniversityRepository) {
           UniversityRepository = xUniversityRepository;
        }
 public UniversityEntitiy saveData(final UniversityEntitiy universityEntitiy) {
     universityRepository.save(universityEntitiy);
    return universityEntitiy;
}
    public UniversityEntitiy find(Long id){
        return universityRepository.findById(id).orElse(null);
    }

    public List<UniversityEntitiy> getAllUniversity() {
        return universityRepository.findAll();
    }

    public  List<UniversityEntitiy> GetByName(String name){

        return universityRepository.findByName(name.toLowerCase());
    }
    public UniversityEntitiy getUniversityById(final Long id) {
        return universityRepository.findById(id).orElse(null);
    }
    public Boolean isUniversityExist(String identificationNumber,String name) {

        return  universityRepository.findByIdOrName(identificationNumber,name) != null ? true : false;
    }

}
