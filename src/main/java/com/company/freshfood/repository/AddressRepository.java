package com.company.freshfood.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.freshfood.entity.AddressEntity;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

}
