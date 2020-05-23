package com.dim.sigepark.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.ExposureConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import com.dim.sigepark.entity.Plaza;
import com.dim.sigepark.entity.Ticket;

@Configuration
public class RestConfig implements RepositoryRestConfigurer {
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration restConfig) {
		ExposureConfiguration config = restConfig.getExposureConfiguration();
		// desactivamos los metodos delete de Ticket y Class para que no puedan ser
		// eliminados desde el front
		config.forDomainType(Ticket.class)
				.withItemExposure((metadata, httpMethods) -> httpMethods.disable(HttpMethod.DELETE));
		config.forDomainType(Plaza.class)
				.withItemExposure((metadata, httpMethods) -> httpMethods.disable(HttpMethod.DELETE));
		// config.forDomainType(Ticket.class).withItemExposure((metadata, httpMethods)
		// -> httpMethods.disable(HttpMethod.PUT));

	}
}
