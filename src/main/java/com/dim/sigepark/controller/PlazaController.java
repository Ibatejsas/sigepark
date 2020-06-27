package com.dim.sigepark.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

import com.dim.sigepark.entity.Plaza;
import com.dim.sigepark.repository.PlazaDAO;


// Spring data rest nos permite exponer operaciones crud contra ellos
// Para exponer o personalizar endpoints existentes
@RepositoryRestController
public class PlazaController {

	//Indicamos a Spring que inyecte esta dependencia
	@Autowired
	PlazaDAO plazaDAO;

	//Indicamos que el metodo es un delete
	@DeleteMapping("/plazas")
	@ResponseStatus(value = HttpStatus.OK)
	public void deletePlaza() {
		List<Plaza> plazas = plazaDAO.findByOcupado(false);
		if (plazas.size() > 0) {
			Plaza plazaABorrar = plazas.get(plazas.size() - 1);
			plazaDAO.delete(plazaABorrar);
		}else {
			throw new HttpClientErrorException(HttpStatus.PRECONDITION_FAILED, "No se pueden borrar mas plazas");
		}
	}

}
