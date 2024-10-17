package com.postgre.springapipostgre.Repositories;

import com.postgre.springapipostgre.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
