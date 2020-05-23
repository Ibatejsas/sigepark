package com.dim.sigepark.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.dim.sigepark.entity.Plaza;

@RepositoryRestResource // indicamos que es un repositorio, ahora Rest
public interface PlazaDAO extends JpaRepository<Plaza, Long> {
	// me esta proporcionado funcionalidad jpa, y aparte me permite definer metodos
	// propios. estudiar metodos CRUD que se implementan en JpaRepsoitory
	// El objetivo es desacoplar BD - codigo

	/*
	 * listado de Verbos en POSTMAN: 
	 * GET {{url}}/api/plazas/ - OBTENER TODAS LAS PLAZAS
	 * POST {{url}}/api/plazas/ - INSERTAR PLAZAS
	 * GET {{url}}/api/plazas/ID - OBTENER POR ID
	 * GET {{url}}/api/plazas/{{id_plaza}}/tickets - OBTENER LOS TICKETS ASIGNADOS A UNA PLAZA
	 * GET {{url}}/api/plazas/search/porOcupado/?ocupado=false MOSTRAR POR PLAZAS LIBRES
	 * GET {{url}}/api - MOSTRAR TODOS LOS RECURSOS DE LA API
	 */

	@RestResource(path = "porOcupado")
	List<Plaza> findByOcupado(@Param("ocupado") Boolean ocupado);

	@RestResource
	long countByOcupado(@Param("ocupado") Boolean ocupado);

}
