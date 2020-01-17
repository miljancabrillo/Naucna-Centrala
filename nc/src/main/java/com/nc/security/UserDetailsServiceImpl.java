package com.nc.security;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.identity.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nc.repository.UserDetailsRepository;


@Service   // It has to be annotated with @Service.
public class UserDetailsServiceImpl implements UserDetailsService  {
	
	@Autowired
	UserDetailsRepository repository;
	
	@Autowired
	IdentityService identityService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		com.nc.model.UserDetails userDetails = repository.findUserDetailsByUsername(username);
		
		if(userDetails == null) {
			throw new UsernameNotFoundException("Username: " + username + " not found");
		}
		
		List<Group> groups = identityService.createGroupQuery().groupMember(userDetails.getUsername()).list();
		
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		
		if(groups!=null) {
			// Remember that Spring needs roles to be in this format: "ROLE_" + userRole (i.e. "ROLE_ADMIN")
			// So, we need to set it to that format, so we can verify and compare roles (i.e. hasRole("ADMIN")).
			
			
			for(Group group : groups) {
				SimpleGrantedAuthority sga = new SimpleGrantedAuthority("ROLE_"+group.getId());
				grantedAuthorities.add(sga);
			}
		}
		
								
		// The "User" class is provided by Spring and represents a model class for user to be returned by UserDetailsService
		// And used by auth manager to verify and check user authentication.
		return new User(userDetails.getUsername(), userDetails.getPassword(), grantedAuthorities);
	}
}