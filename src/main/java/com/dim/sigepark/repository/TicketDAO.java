package com.dim.sigepark.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.dim.sigepark.entity.Ticket;

@RepositoryRestResource
public interface TicketDAO extends JpaRepository<Ticket, Long>, TicketDAOCustom<Ticket> {

	// TODO
	// filtar por pagado y matricula

	/*listado de Verbos  en POSTMAN:
	 * GET {{url}}/api/tickets - GET TICKETS
	 * POST {{url}}/api/tickets - POST TICKETS
	 * PATH {{url}}/api/tickets/ID_TICKET -  PAGAR TICKETS
	 * GET {{url}}/api/tickets/{{id_ticket}} - GET BY ID
	 * GET {{url}}/api/tickets/{{id_ticket}}/plaza - GET PLAZA POR NUMERO DE TICKET
	 * PUT {{url}}/api/tickets/{{id_ticket}}/plaza - ASIGNAR PLAZA A TICKET
	 * DEL {{url}}/api/tickets/{{id_ticket}}/plaza - DESASIGNAR PLAZA A TICKET(PROHIBIDO EN RESTCONFIG)
	 * DEL {{url}}/api/tickets/{{id_ticket}} - BORRAR TICKET (PROHIBIDO EN RESTCONFIG)
	 */
	
	
	// no consigo eliminar el warning de ticket, investigar ¿puede ser un error de
	// Eclipse?
	@RestResource(exported = false)
	Ticket save(Ticket entity);

	@RestResource(path = "entrada-despues-de")
	List<Ticket> findByEntradaAfter(@Param("entrada") LocalDateTime entrada);

	@RestResource(path = "porMatricula")
	List<Ticket> findByMatriculaIgnoreCaseContaining(@Param("matricula") String matricula);

	@RestResource(path = "porPagado")
	List<Ticket> findByPagado(@Param("pagado") Boolean pagado);

}
