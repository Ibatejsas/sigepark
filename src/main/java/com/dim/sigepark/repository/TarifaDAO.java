package com.dim.sigepark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import com.dim.sigepark.entity.Tarifa;


@RepositoryRestResource
public interface TarifaDAO extends JpaRepository<Tarifa, Long>{

	@RestResource(exported = false)
	void delete(Tarifa entity); 
	
	@RestResource(exported = false)
	void deleteById(Long id);
	
}
