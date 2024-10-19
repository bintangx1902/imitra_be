package com.postgre.springapipostgre.Repositories;

import com.postgre.springapipostgre.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
