package com.dim.sigepark.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Tarifa {

	@Id // Clave principal en la base de datos de la tabla tarifa
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Delega la estrategia a la base de datos, en H2
	private long id;

	@Column(nullable = false)
	private double factor;

	@Column(nullable = false)
	private FactorTiempo factor_tiempo;
	
	
	//El campo tipo define una tarifa en concreto por eso es único
	//Se podria hacer una tabla de tarifas
	@Column(nullable = false, unique = true)
	private Type tipo;
	
	
	public enum Type {
	    NORMAL, FIN_SEMANA
	}

	public enum FactorTiempo {
		MINUTO, HORA
	}

	@OneToMany(mappedBy = "tarifa")
	private List<Ticket> tickets;

	public Tarifa(double factor, FactorTiempo factorTiempo, Type tipo) {
		super();
		this.factor = factor;
		this.factorTiempo = factorTiempo;
		this.tipo = tipo;
	}

	protected Tarifa() {
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public double getFactor() {
		return factor;
	}

	public void setFactor(double factor) {
		this.factor = factor;
	}

	public FactorTiempo getFactorTiempo() {
		return factorTiempo;
	}

	public void setFactorTiempo(FactorTiempo factorTiempo) {
		this.factorTiempo = factorTiempo;
	}

    public Type getTipo() {
        return tipo;
    }

    public void setTipo(Type tipo) {
        this.tipo = tipo;
    }
	
	

}
