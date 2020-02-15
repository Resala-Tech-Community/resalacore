
package org.resala.core.volunteer.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class GeneralRepository<E, K> {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void updateRegisterCount(Long branchId, Long eventId) {
        String statement = "update RegistrationVolunteerCountEntity set count = count+1 where branchId=?1 and eventId=?2";
        jdbcTemplate.update(statement, branchId, eventId);
    }

}

