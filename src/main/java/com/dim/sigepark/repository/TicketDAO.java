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

	@RestResource(exported = false) //para que no lo exponga, y personalizamos en el ticket controller
	Ticket save(Ticket entity);

	@RestResource(path = "entrada-despues-de")
	List<Ticket> findByEntradaAfter(@Param("entrada") LocalDateTime entrada);

	@RestResource(path = "por-matricula")
	List<Ticket> findByMatriculaIgnoreCaseContaining(@Param("matricula") String matricula);

	@RestResource(path = "por-pagado")
	List<Ticket> findByPagado(@Param("pagado") Boolean pagado);
	
	@RestResource(path = "por-matricula-y-pagado")
	List<Ticket> findByMatriculaAndPagado(@Param("matricula") String matricula, @Param("pagado") Boolean pagado);

}
