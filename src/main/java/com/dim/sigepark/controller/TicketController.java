package com.dim.sigepark.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;

import com.dim.sigepark.entity.Plaza;
import com.dim.sigepark.entity.Ticket;
import com.dim.sigepark.repository.PlazaDAO;
import com.dim.sigepark.repository.TicketDAO;

@RepositoryRestController
public class TicketController {

	// inyectamos las dependencias para usarlas en la clase:
	@Autowired
	PlazaDAO plazaDAO;

	@Autowired
	TicketDAO ticketDAO;

	// Logica para ocupar plazas
	@PostMapping("/tickets")
	@ResponseBody
	public PersistentEntityResource postTicket(@RequestBody Ticket ticket,
			PersistentEntityResourceAssembler assembler) {

		List<Plaza> plazasLibres = plazaDAO.findByOcupado(false);

		if (plazasLibres.size() > 0) {

			Plaza plaza = plazasLibres.stream().findFirst().get();
			plaza.setOcupado(true);

			ticket.setPlaza(plaza);
			Ticket newTicket = ticketDAO.save(ticket);
			plazaDAO.save(plaza);

			return assembler.toModel(newTicket);
		}

		throw new HttpClientErrorException(HttpStatus.PRECONDITION_FAILED, "No hay plazas libres");
	}

	// obtener tickets
	@PatchMapping("/tickets/{id}")
	@ResponseBody
	public PersistentEntityResource patchTicket(@PathVariable Long id, PersistentEntityResourceAssembler assembler) {

		Ticket ticketUpdated = ticketDAO.getOne(id);

		if (plazaDAO.findByOcupado(true).size() > 0 && ticketUpdated != null && !ticketUpdated.isPagado()) {

			ticketUpdated.setSalida(LocalDateTime.now());
			ticketUpdated.setPagado(true);

			Plaza plaza = ticketUpdated.getPlaza();
			plaza.setOcupado(false);

			Ticket newTicket = ticketDAO.save(ticketUpdated);
			plazaDAO.save(plaza);

			return assembler.toModel(newTicket);

		}

		throw new HttpClientErrorException(HttpStatus.PRECONDITION_FAILED, "Tu coche no esta en el parking");
	}
}
