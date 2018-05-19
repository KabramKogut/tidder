package com.tidder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tidder.model.UserEntity;

public interface LoginRepository extends JpaRepository<UserEntity, Integer> {

	@Query("SELECT u FROM UserEntity u WHERE u.email = :email")
	public UserEntity findByEmail(@Param("email") String email);

}
