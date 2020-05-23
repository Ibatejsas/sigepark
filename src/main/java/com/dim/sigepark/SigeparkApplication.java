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

	// Vamos a utilizar la clase de configuracion para implementar 20 plazas
	// iniciales al parking.
	private static void initPlazas(PlazaDAO plazaDAO, ParkingProperties parkingProperties) {
		Plaza plaza;
		for (int i = 0; i < parkingProperties.getNumber(); i++) {
			plaza = new Plaza(false);
			plazaDAO.save(plaza);
		}
	}

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(SigeparkApplication.class, args);
		PlazaDAO plazaDAO = context.getBean(PlazaDAO.class);
		ParkingProperties parkingProperties = context.getBean(ParkingProperties.class);

		initPlazas(plazaDAO, parkingProperties);
	}

}
