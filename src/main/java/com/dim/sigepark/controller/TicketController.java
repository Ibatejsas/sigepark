package com.dim.sigepark.controller;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
import com.dim.sigepark.entity.Tarifa;
import com.dim.sigepark.entity.Ticket;
import com.dim.sigepark.repository.PlazaDAO;
import com.dim.sigepark.repository.TarifaDAO;
import com.dim.sigepark.repository.TicketDAO;

@RepositoryRestController
public class TicketController {

	// inyectamos las dependencias para usarlas en la clase:
	@Autowired
	PlazaDAO plazaDAO;

	@Autowired
	TicketDAO ticketDAO;
	
	@Autowired
	TarifaDAO tarifaDAO;

	// Logica para ocupar plazas
	@PostMapping("/tickets")
	@ResponseBody
	public PersistentEntityResource postTicket(@RequestBody Ticket ticket,
			PersistentEntityResourceAssembler assembler) {

		List<Plaza> plazasLibres = plazaDAO.findByOcupado(false);

		if (plazasLibres.size() > 0) {
			if (ticketDAO.findByMatriculaAndPagado(ticket.getMatricula(), false).size()>0) {
				throw new HttpClientErrorException(HttpStatus.PRECONDITION_FAILED, "Matricula duplicada");
			}
			Plaza plaza = plazasLibres.stream().findFirst().get();
			plaza.setOcupado(true);

			
	         if(isWeekend()) {
	             ticket.setTarifa(tarifaDAO.findTarifaByTipo(Tarifa.Type.FIN_SEMANA));
	         }
	         else {
	             ticket.setTarifa(tarifaDAO.findTarifaByTipo(Tarifa.Type.NORMAL));
	         }
            
			
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
		List<Plaza> plazasOcupadas = plazaDAO.findByOcupado(true);

		if (plazaDAO.findByOcupado(true).size() > 0 && ticketUpdated != null && !ticketUpdated.isPagado()) {

			ticketUpdated.setSalida(LocalDateTime.now(ZoneId.of("Europe/Paris")));
			ticketUpdated.setPagado(true);

			Plaza plaza = plazasOcupadas.get(0);
			plaza.setOcupado(false);

			Ticket newTicket = ticketDAO.save(ticketUpdated);
			plazaDAO.save(plaza);

			return assembler.toModel(newTicket);

		}

		throw new HttpClientErrorException(HttpStatus.PRECONDITION_FAILED, "Tu coche no esta en el parking");
	}
	
	//Seleccionamos fin de semana y zona horaria europea
	private boolean isWeekend() {
	    
	    DayOfWeek dia = LocalDateTime.now(ZoneId.of("Europe/Paris")).getDayOfWeek();
       
	    return dia == DayOfWeek.SATURDAY || dia == DayOfWeek.SUNDAY;
	}
}
