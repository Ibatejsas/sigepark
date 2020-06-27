package com.dim.sigepark;

import java.io.BufferedReader;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import com.dim.sigepark.config.ConfiguracionPorJava;
import com.dim.sigepark.entity.Plaza;
import com.dim.sigepark.entity.Tarifa;
import com.dim.sigepark.repository.PlazaDAO;
import com.dim.sigepark.repository.TarifaDAO;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;


//Autoconfiguracion de Spring Boot
//@ImportResource indicamos la configuracion por xml
//

@SpringBootApplication
@ImportResource({"classpath:config/jpa-config.xml"})
//@Import(ConfiguracionPorJava.class) ¿Es necesario al tener el 
//Component Scan en Configuracion por java?
public class SigeparkApplication {
	
	private static final Logger log = LoggerFactory.getLogger(SigeparkApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SigeparkApplication.class, args);
		
		ObjectMapper mapper = context.getBean(ObjectMapper.class);
		
		PlazaDAO plazaDAO = context.getBean(PlazaDAO.class);
		TarifaDAO tarifaDAO = context.getBean(TarifaDAO.class);
		
		cargarPlazasDesdeArchivo("src/main/resources/plazas.json", mapper, plazaDAO);
		cargarTarifasDesdeArchivo("src/main/resources/tarifas.json", mapper, tarifaDAO);
	}
	
	static void cargarPlazasDesdeArchivo(String ruta, ObjectMapper mapper, PlazaDAO plazaDAO) {
		String linea = null;
		mapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		try (BufferedReader buffer = new BufferedReader(
				new InputStreamReader(new FileInputStream(ruta), "UTF-8"))) {
			Plaza plaza;
			while((linea = buffer.readLine()) != null) {
				if (linea.startsWith("{") && linea.endsWith("}")) {
					plaza = mapper.readValue(linea, Plaza.class);
					plazaDAO.save(plaza);
					log.trace("Cargado {}", plaza);
				}
			}
		} catch (Exception e) {
			log.error("Error leyendo: {}: {}", linea, e.getMessage());
		}
	}
	

	static void cargarTarifasDesdeArchivo(String ruta, ObjectMapper mapper, TarifaDAO tarifaDAO) {
		String linea = null;
		mapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		try (BufferedReader buffer = new BufferedReader(
				new InputStreamReader(new FileInputStream(ruta), "UTF-8"))) {
			Tarifa tarifa;
			while((linea = buffer.readLine()) != null) {
				if (linea.startsWith("{") && linea.endsWith("}")) {
					tarifa = mapper.readValue(linea, Tarifa.class);
					tarifaDAO.save(tarifa);
					log.trace("Cargado {}", tarifa);
				}
			}
		} catch (Exception e) {
			log.error("Error leyendo: {}: {}", linea, e.getMessage());
		}
	}
}
