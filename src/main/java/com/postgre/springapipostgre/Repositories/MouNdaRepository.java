package com.postgre.springapipostgre.Repositories;

import com.postgre.springapipostgre.models.MouNda;
import com.postgre.springapipostgre.models.enums.StatusChoices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MouNdaRepository extends JpaRepository<MouNda, Long> {
    List<MouNda> findByStatusIn(List<StatusChoices> status);
}
