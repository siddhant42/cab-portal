package com.portal.cabmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.portal.cabmanagement.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByEmail(String email);
//	Optional<User> findById(String id);
//    @Query("SELECT u FROM User u WHERE u.id = :id")
//    public User getUserById(@Param("id") int id);

	Optional<User> findByUsername(String username);
}
