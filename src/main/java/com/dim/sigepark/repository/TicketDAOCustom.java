package com.dim.sigepark.repository;

import com.dim.sigepark.entity.Ticket;

public interface TicketDAOCustom<T extends Ticket> {

	T myMethod(T entity);

}
