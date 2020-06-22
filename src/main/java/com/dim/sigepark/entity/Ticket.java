package com.dim.sigepark.entity;

import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.persistence.Column;
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

	@Column(nullable = false)
	private String matricula;

	@Column(nullable = false)
	private boolean pagado = false;

	@Column(nullable = false, columnDefinition = "TIMESTAMP") //le indicamos a JPA que es un localdatetime
	private LocalDateTime entrada = LocalDateTime.now(ZoneId.of("Europe/Paris"));

	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime salida;

	@ManyToOne
	@JoinColumn(name = "plaza_id")
	private Plaza plaza;
	
	@ManyToOne
	@JoinColumn(name = "tarifa_id")
	private Tarifa tarifa;

	protected Ticket() {
		super();
	}

	public Ticket(String matricula, boolean pagado, LocalDateTime entrada, LocalDateTime salida, Plaza plaza,
			Tarifa tarifa) {
		super();
		this.matricula = matricula;
		this.pagado = pagado;
		this.entrada = entrada;
		this.salida = salida;
		this.plaza = plaza;
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

	public Plaza getPlaza() {
		return plaza;
	}

	public void setPlaza(Plaza plaza) {
		this.plaza = plaza;
	}

}
