package com.github.queebskeleton.hardwarecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.github.queebskeleton.hardwarecommerce.entity.Vendor;
import com.github.queebskeleton.hardwarecommerce.entity.projection.IVendorProductCount;

@Repository
public interface VendorJpaRepository extends JpaRepository<Vendor, Long>, JpaSpecificationExecutor<Vendor> {

	@Query("SELECT v AS vendor, COUNT(p.id) AS totalProductCount FROM Product p RIGHT OUTER JOIN p.vendor v"
			+ " GROUP BY v.id")
	List<IVendorProductCount> findAllWithProductCount();
	
}