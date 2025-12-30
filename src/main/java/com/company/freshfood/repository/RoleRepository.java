package com.company.freshfood.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.freshfood.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

}
