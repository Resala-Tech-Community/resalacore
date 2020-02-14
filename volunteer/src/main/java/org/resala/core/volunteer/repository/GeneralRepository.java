/*
package org.resala.core.volunteer.repository;

import org.resala.core.volunteer.entities.SimpleEntityParent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@NoRepositoryBean
public interface GeneralRepository<T> extends JpaRepository<T, Long> {

    @Query(
            value = "update RegistrationVolunteerCountEntity set count = count+1 where branchId=?1 and eventId=?2",
            nativeQuery = true
    )
    void insertCountIntoSelectCount(Long branchId, Long eventId);
}
*/
