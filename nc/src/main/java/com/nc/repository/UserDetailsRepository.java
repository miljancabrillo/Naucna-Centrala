package com.nc.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nc.model.UserDetails;

@SuppressWarnings("unchecked")
@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {

	public UserDetails save(UserDetails ud);
	
	public UserDetails findUserDetailsByUsername(String username);
	
	public List<UserDetails> findAll();

	List<UserDetails> findByNameContainingIgnoreCase(String name);

}
