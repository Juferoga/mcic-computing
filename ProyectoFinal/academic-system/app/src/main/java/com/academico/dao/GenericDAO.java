package com.academico.dao;

import java.util.List;

public interface GenericDAO<T> {
    boolean insertar(T entidad);
    boolean modificar(T entidad);
    boolean eliminar(String id);
    List<T> listarTodos();
    T buscarPorId(String id);
}