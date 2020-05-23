package com.dim.sigepark.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Plaza {

	@Id // Calve principal en la base de datos de la tabla plaza
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Delega la estrategia a la base de datos, en H2
	private Long id;
	private Boolean ocupado;

	// Propiedad OneToMany, de una Plaza se pueden obtener la lista de tickets que
	// la han ocupado
	@OneToMany(mappedBy = "plaza")
	private List<Ticket> tickets;

	protected Plaza() {
		super();
	}

	public Plaza(Boolean ocupado) {
		super();
		this.ocupado = ocupado;
	}

	// setId se elimina porque se genera automaticamente
	public Long getId() {
		return id;
	}

	public Boolean getOcupado() {
		return ocupado;
	}

	public void setOcupado(Boolean ocupado) {
		this.ocupado = ocupado;
	}

	// anadimos el getter de tickets al anadir la lista de Tickets
	public List<Ticket> getTickets() {
		return tickets;
	}

}
