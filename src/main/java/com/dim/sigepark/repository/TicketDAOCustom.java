package com.dim.sigepark.repository;

import com.dim.sigepark.entity.Ticket;

// utilizamos los custom para extender el JPA repository
// definimos metodos propios a traves de un interface normal
// sin uso

public interface TicketDAOCustom<T extends Ticket> {

	T myMethod(T entity);

}
