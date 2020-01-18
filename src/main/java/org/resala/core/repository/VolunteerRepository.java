package org.resala.core.repository;

import org.resala.core.entities.VolunteerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VolunteerRepository extends JpaRepository<VolunteerEntity, Long> {

    @Query("select u from VolunteerEntity u where LOWER(u.name) like %?1%")
    List<VolunteerEntity> findByName(String Name);
    @Query("select u from VolunteerEntity u where u.identificationNumber = ?1 or u.phoneNumber = ?2")
    VolunteerEntity findByIdOrPhone(String identificationNumber,String phone);
    @Query("select  max(u.code) from VolunteerEntity u where u.code like ?1%")
    Long findMaxCode(String firstSegment);


}
