package com.school.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.entity.Role;

@Repository
public interface RolesRepository extends JpaRepository<Role, Integer> {
	Optional<Role> findByName(String name);
}