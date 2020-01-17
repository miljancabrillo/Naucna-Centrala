package com.nc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nc.model.UserDetails;

@SuppressWarnings("unchecked")
@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {

	public UserDetails save(UserDetails ud);
	
	public UserDetails findUserDetailsByUsername(String username);
	
	public List<UserDetails> findAll();
	
}
