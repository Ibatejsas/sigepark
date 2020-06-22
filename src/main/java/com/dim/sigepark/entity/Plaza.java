package com.dim.sigepark.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Plaza {

	@Id // Clave principal en la base de datos de la tabla plaza
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Delega la estrategia a la base de datos, en H2
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
