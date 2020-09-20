package com.github.queebskeleton.hardwarecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.queebskeleton.hardwarecommerce.entity.Address;

@Repository
public interface AddressJpaRepository extends JpaRepository<Address, Long> {

}
