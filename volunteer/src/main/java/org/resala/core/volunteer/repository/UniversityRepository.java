package org.resala.core.volunteer.repository;
import org.resala.core.volunteer.entities.UniversityEntitiy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface UniversityRepository extends JpaRepository<UniversityEntitiy, Long>{

    @Query("select u from UniversityEntitiy u where LOWER(u.name) like %?1%")
        List<UniversityEntitiy> findByName(String Name);

        //todo:to be done in database not in code
        @Query("select u from UniversityEntitiy u where u.id= ?1 or u.name = ?2")
        UniversityEntitiy findByIdOrName(String Id, String Name);

        @Query("select  max(u.name) from UniversityEntitiy u where u.name like ?1%")
        Long findMaxCode(String firstSegment);
    }

