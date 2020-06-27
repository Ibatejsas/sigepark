package com.dim.sigepark.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.dim.sigepark.entity.Plaza;
import com.dim.sigepark.entity.Tarifa;
import com.dim.sigepark.entity.Ticket;
import com.fasterxml.jackson.databind.ObjectMapper;



@Configuration
@PropertySource({ "classpath:config/rest.properties", 
"classpath:config/jackson.properties" })
@ComponentScan("com.dim.sigepark")
public class ConfiguracionPorJava {

	
	@Bean
	public ObjectMapper getObjectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		//Customizar la respuesta de la BD
		//@JsonComponent customiza la entrada en la BD
		mapper.addMixIn(Plaza.class, MixIns.Plazas.class);
		mapper.addMixIn(Ticket.class, MixIns.Tickets.class);
		mapper.addMixIn(Tarifa.class, MixIns.Tarifas.class);
		return mapper;
	}
	

	
}
