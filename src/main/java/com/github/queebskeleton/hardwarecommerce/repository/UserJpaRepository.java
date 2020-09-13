package com.github.queebskeleton.hardwarecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.queebskeleton.hardwarecommerce.entity.User;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {

}
