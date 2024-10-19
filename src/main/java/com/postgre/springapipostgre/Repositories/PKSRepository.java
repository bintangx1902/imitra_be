package com.postgre.springapipostgre.Repositories;

import com.postgre.springapipostgre.models.PKS;
import com.postgre.springapipostgre.models.enums.StatusChoices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PKSRepository extends JpaRepository<PKS, Long> {
    List<PKS> findByStatusIn(List<StatusChoices> status);
}
