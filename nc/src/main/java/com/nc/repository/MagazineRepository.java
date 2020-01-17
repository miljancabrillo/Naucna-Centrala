package com.nc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nc.model.Magazine;

@SuppressWarnings("unchecked")
@Repository
public interface MagazineRepository extends JpaRepository<Magazine, Long> {

	public Magazine save(Magazine m);
	
	public Magazine findOneById(long id);
	
}
