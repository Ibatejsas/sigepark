package com.dim.sigepark.config;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.ExposureConfiguration;
import org.springframework.data.rest.webmvc.RepositorySearchesResource;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.dim.sigepark.entity.Ticket;

/**
 * Configuracion de uso generalizado para distintos proyectos Spring Data Rest.
 * Proporciona las siguientes funcionalidades:
 * <ol>
 * <li>{@link #addSearchLinks(RepositoryRestConfiguration)}: enlaza cada
 * {@code /recursos/search} automaticamente con los metodos de los controladores
 * registrados.</li>
 * <li>{@link #corsFilter()}: permite cualquier solicitud Cross-Origin.</li>
 * </ol>
 * 
 * @author <a href="https://github.com/Awes0meM4n">Awes0meM4n</a>
 * @version 1.0
 * @since 1.0
 */
@Configuration
public class ConfiguracionRest implements RepositoryRestConfigurer {

	/**
	 * Ver tambien <a href=
	 * "https://docs.spring.io/spring-data/rest/docs/current/reference/html/#customizing-sdr.configuring-cors">
	 * Configuring CORS</a> para configuracion Data Rest adicional heredada con
	 * {@link org.springframework.web.bind.annotation.CrossOrigin}.
	 * 
	 * @return bean del tipo {@link CorsFilter} permitiendo cualquier solicitud
	 */
	@Bean
	CorsFilter corsFilter() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.setAllowedOrigins(Collections.singletonList("*"));
		config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept"));
		config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH"));
		source.registerCorsConfiguration("/**", config);

		return new CorsFilter(source);
	}


	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration restConfig) {
		ExposureConfiguration config = restConfig.getExposureConfiguration();
		// desactivamos los metodos delete de Ticket y Class para que no puedan ser
		// eliminados desde el front
		config.forDomainType(Ticket.class)
				.withItemExposure((metadata, httpMethods) -> httpMethods.disable(HttpMethod.DELETE));
		// config.forDomainType(Plaza.class)
		// .withItemExposure((metadata, httpMethods) ->
		// httpMethods.disable(HttpMethod.DELETE));
		// config.forDomainType(Ticket.class).withItemExposure((metadata, httpMethods)
		// -> httpMethods.disable(HttpMethod.PUT));

	}
	

}