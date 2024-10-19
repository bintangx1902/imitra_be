package com.postgre.springapipostgre.Repositories;

import com.postgre.springapipostgre.models.RAB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RabRepository extends JpaRepository<RAB, Long> {
}
