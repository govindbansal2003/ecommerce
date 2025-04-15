package com.ecommerce.repository;

import com.ecommerce.Entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  AddressRepository extends JpaRepository<Address,Long> {
}
