package com.dim.sigepark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import com.dim.sigepark.config.ParkingProperties;
import com.dim.sigepark.entity.Plaza;
import com.dim.sigepark.repository.PlazaDAO;

//Autoconfiguracion de Spring Boot

@SpringBootApplication
public class SigeparkApplication {

	// Vamos a utilizar la clase de configuracion para añadir 10 plazas iniciales al
	// parking.
	private static void initPlazas(PlazaDAO plazaDAO, ParkingProperties parkingProperties) {
		Plaza plaza;
		// Pasar a lambda
		for (int i = 0; i < parkingProperties.getNumber(); i++) {
			plaza = new Plaza(false);
			plazaDAO.save(plaza);
		}
	}

	// Esta clase levanta todo el contexto del micro
	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(SigeparkApplication.class, args);
		PlazaDAO plazaDAO = context.getBean(PlazaDAO.class);
		ParkingProperties parkingProperties = context.getBean(ParkingProperties.class);

		// vamos a obtener los BEAN

		initPlazas(plazaDAO, parkingProperties);
	}

}
