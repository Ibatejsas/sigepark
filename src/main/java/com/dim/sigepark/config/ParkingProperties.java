package com.dim.sigepark.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

//Indicamos a Spring que una clase de configuracion
@Configuration
// Levantamos un beam para que los atributos de esta clase se correspondan con 
// los atributos del fichero aplication.properties cuyo prefijo
// es parking
@ConfigurationProperties(prefix = "parking")
public class ParkingProperties {

//Ejemplo de uso de clase de configuracion, relacionada en application properties
	private int number;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
