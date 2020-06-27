package com.dim.sigepark.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


// Se deja una entidad como ejemplo de uso de @Entity en vez de ORM por XML
@Entity
public class Plaza {
	
	@Id // Clave principal en la base de datos de la tabla plaza
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Delega la estrategia a la base de datos
	private Long id;
	
	@Column(nullable = false)
	private Boolean ocupado;

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

}
