package com.dim.sigepark.config;

import java.time.LocalDateTime;
import java.util.List;
import com.dim.sigepark.entity.Tarifa;
import com.dim.sigepark.entity.Tarifa.FactorTiempo;
import com.dim.sigepark.entity.Tarifa.Type;
import com.dim.sigepark.entity.Ticket;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


// Los MixIns es una forma de indicar a Jackson como mapear los Json a
// clases java
public class MixIns {

	@JsonPropertyOrder({ "id", "ocupado" })
	public static interface Plazas {

		@JsonProperty("id")
		abstract long getId();

		@JsonProperty("ocupado")
		abstract boolean getOcupado();
	}

	@JsonPropertyOrder({ "id", "matricula", "pagado", "entrada", "salida", "tarifa" })
	public static interface Tickets {

		@JsonProperty("id")
		abstract long getId();

		@JsonProperty("matricula")
		abstract String getMatricula();

		@JsonProperty("pagado")
		abstract boolean getPagado();

		@JsonProperty("entrada")
		abstract LocalDateTime getEntrada();

		@JsonProperty("salida")
		abstract LocalDateTime getSalida();

		@JsonProperty("tarifa")
		abstract Tarifa getTarifa();
	}

	@JsonIgnoreProperties(value = { "tickets" })
	@JsonPropertyOrder({ "id", "factor", "factorTiempo", "tipo" })
	public static interface Tarifas {

		@JsonProperty("id")
		abstract long getId();

		@JsonProperty("factor")
		abstract double getFactor();
		
		@JsonProperty("factorTiempo")
		abstract FactorTiempo getFactorTiempo();
		
		@JsonProperty("tipo")
		abstract Type getTipo();
		
		@JsonProperty("tickets")
		abstract List<Ticket> getTickets();
		
	}
}
