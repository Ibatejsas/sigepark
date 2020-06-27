package com.dim.sigepark.entity;

import java.util.List;

import javax.persistence.OneToMany;

public class Tarifa {

	private long id;
	private double factor;
	private FactorTiempo factorTiempo;
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
