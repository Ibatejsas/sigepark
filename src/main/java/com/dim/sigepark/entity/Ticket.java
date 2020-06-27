package com.dim.sigepark.entity;

import java.time.LocalDateTime;

import java.time.ZoneId;

import javax.persistence.ManyToOne;

public class Ticket {

	private long id;
	private String matricula;
	private boolean pagado = false;
	private LocalDateTime entrada = LocalDateTime.now(ZoneId.of("Europe/Paris"));
	private LocalDateTime salida;
	
	@ManyToOne
	private Tarifa tarifa;

	protected Ticket() {
		super();
	}

	public Ticket(String matricula, boolean pagado, LocalDateTime entrada, LocalDateTime salida, Tarifa tarifa) {
		super();
		this.matricula = matricula;
		this.pagado = pagado;
		this.entrada = entrada;
		this.salida = salida;
		this.tarifa = tarifa;
	}

	public Tarifa getTarifa() {
		return tarifa;
	}

	public void setTarifa(Tarifa tarifa) {
		this.tarifa = tarifa;
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

}
