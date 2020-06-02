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

@RepositoryRestController
public class PlazaController {

	@Autowired
	PlazaDAO plazaDAO;

	@DeleteMapping("/plazas")
	@ResponseStatus(value = HttpStatus.OK)
	public void deletePlaza() {
		List<Plaza> plazas = plazaDAO.findAll();
		if (plazas.size() > 0) {
			Plaza plazaABorrar = plazas.get(plazas.size() - 1);
			plazaDAO.delete(plazaABorrar);
		}else {
			throw new HttpClientErrorException(HttpStatus.PRECONDITION_FAILED, "No se pueden borrar mas plazas");
		}
	}

}
