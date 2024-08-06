package com.dlearning.repository;

import com.dlearning.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    // JpaSpecificationExecutor: 


    User findByUsername(String username);
    User findByEmail(String email);
}
