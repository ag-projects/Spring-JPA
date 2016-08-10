package com.agharibi.services;

import java.util.List;

public interface CRUDService<T> {
	List<?> listAll();
	T getBy(Integer id);
	T saveOrUpdate(T object);
	void delete(Integer id);
}
