package com.dlearning.repository;

import com.dlearning.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    // JpaSpecificationExecutor: 


    User findByUsername(String username);
    User findByEmail(String email);
    Page<User> findAll(Specification<User> spec, Pageable pageable);
}
