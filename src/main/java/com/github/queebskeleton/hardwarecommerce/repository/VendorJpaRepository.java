package com.github.queebskeleton.hardwarecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.github.queebskeleton.hardwarecommerce.entity.Vendor;

@Repository
public interface VendorJpaRepository extends JpaRepository<Vendor, Long>, JpaSpecificationExecutor<Vendor> {

}
