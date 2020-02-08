package com.nc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nc.model.ScientificArea;

@SuppressWarnings("unchecked")
@Repository
public interface ScientificAreaRepository extends JpaRepository<ScientificArea, Long> {

	public ScientificArea save(ScientificArea sa);
	
	public ScientificArea findOneById(String id);
	
	public List<ScientificArea> findAll();
}

