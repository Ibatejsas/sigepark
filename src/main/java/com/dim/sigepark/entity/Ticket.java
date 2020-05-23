package com.dim.sigepark.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String matricula;

	private boolean pagado = false;

	private LocalDateTime entrada = LocalDateTime.now();

	private LocalDateTime salida;

	@ManyToOne
	@JoinColumn(name = "plaza_id")
	private Plaza plaza;

	// Valorar protected o public antes de entregar?
	protected Ticket() {
		super();
	}

	public Ticket(String matricula, boolean pagado, LocalDateTime entrada, LocalDateTime salida, Plaza plaza) {
		super();
		this.matricula = matricula;
		this.pagado = pagado;
		this.entrada = entrada;
		this.salida = salida;
		this.plaza = plaza;
	}

	public long getId() {
		return id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public boolean isPagado() {
		return pagado;
	}

	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}

	public LocalDateTime getEntrada() {
		return entrada;
	}

	public void setEntrada(LocalDateTime entrada) {
		this.entrada = entrada;
	}

	public LocalDateTime getSalida() {
		return salida;
	}

	public void setSalida(LocalDateTime salida) {
		this.salida = salida;
	}

	public Plaza getPlaza() {
		return plaza;
	}

	public void setPlaza(Plaza plaza) {
		this.plaza = plaza;
	}

}
