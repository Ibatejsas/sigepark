package com.dim.sigepark.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.dim.sigepark.entity.Plaza;

// los DAO son repositorios REST
// Repository es la capa ORM, que es la que se encarga de hablar con la BD.
@RepositoryRestResource
public interface PlazaDAO extends JpaRepository<Plaza, Long> {

	//Query method que se exponen como endpoint REST
	@RestResource(path = "por-ocupado")
	List<Plaza> findByOcupado(@Param("ocupado") Boolean ocupado);

	@RestResource(path = "total-ocupado")
	long countByOcupado(@Param("ocupado") Boolean ocupado);

}
