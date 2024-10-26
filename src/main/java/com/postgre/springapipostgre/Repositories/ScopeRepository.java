package com.postgre.springapipostgre.Repositories;

import com.postgre.springapipostgre.models.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScopeRepository extends JpaRepository<Scope, Long> {
}
