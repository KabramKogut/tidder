package com.tidder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tidder.model.UserEntity;

public interface LoginRepository extends JpaRepository<UserEntity, Integer> {

	UserEntity findByEmail(String email);

}
