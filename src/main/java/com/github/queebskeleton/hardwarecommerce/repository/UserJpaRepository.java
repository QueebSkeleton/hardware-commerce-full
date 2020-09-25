package com.github.queebskeleton.hardwarecommerce.repository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaSpecificationExecutor;
import com.github.queebskeleton.hardwarecommerce.entity.User;

@Repository
public interface UserJpaRepository extends EntityGraphJpaRepository<User, Long>, EntityGraphJpaSpecificationExecutor<User> {
    
    Optional<User> findByUsername(String username);
    
}
