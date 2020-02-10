package com.nc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nc.model.Membership;

@SuppressWarnings("unchecked")
public interface MembershipRepository extends JpaRepository<Membership, Long> {
	
	@Query("select mem from Membership mem where mem.user.username = :username and mem.magazine.id = :magazineId")
	public List<Membership> findByUsernameAndMagazineId(@Param("username") String username, @Param("magazineId") long magazineId);
	
	public Membership save(Membership mem);
	
	public Membership findOneById(long id);
	
}
