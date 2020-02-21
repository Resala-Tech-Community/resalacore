
package org.resala.core.volunteer.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class GeneralRepository<E, K> {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void updateRegisterCount(Long branchId, Long eventId, Long count) {
        String statement = "update RegistrationVolunteerCountEntity set count = ?3 where branchId=?1 and eventId=?2";
        jdbcTemplate.update(statement, branchId, eventId, count);
    }

    public void insertRegisterCount(long branchId, long eventId, long count) {
        String statement = "insert into RegistrationVolunteerCountEntity values( ?1,?2, ?3)";
        jdbcTemplate.update(statement, branchId, eventId, count);
    }
}

