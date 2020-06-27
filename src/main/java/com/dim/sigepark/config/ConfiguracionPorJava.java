package com.dim.sigepark.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import com.dim.sigepark.entity.Plaza;
import com.dim.sigepark.entity.Tarifa;
import com.dim.sigepark.entity.Ticket;
import com.fasterxml.jackson.databind.ObjectMapper;


//Clase de configuracion para definir beans
//Sirve para definir dependencias externas con beans
//Spring busca siempre las propiedades en un fichero application...
//con @PropertySource añadimos otros ficheros a las propiedades de la aplicacion
//@ComponentScan raiz para que Spring busque componentes
@Configuration
@PropertySource({ "classpath:config/rest.properties", "classpath:config/jackson.properties" })
@ComponentScan("com.dim.sigepark")
public class ConfiguracionPorJava {

	// ObjectMapper lo usa la libreria de Json para mapear objetos java
	@Bean
	public ObjectMapper getObjectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		// Customizar la respuesta de la BD
		// @JsonComponent customiza la entrada en la BD
		mapper.addMixIn(Plaza.class, MixIns.Plazas.class);
		mapper.addMixIn(Ticket.class, MixIns.Tickets.class);
		mapper.addMixIn(Tarifa.class, MixIns.Tarifas.class);
		return mapper;
	}

}
