package com.nacu.medicaloffices.services;

import java.util.List;

public interface CrudService<T, ID> {
    List<T> findAll();
    T findById(ID id);
    T create(T object);
    T saveById(ID id, T object);
    void deleteById(ID id);
}
