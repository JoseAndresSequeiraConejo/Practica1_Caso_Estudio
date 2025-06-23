package com.practica01.service;

import com.practica01.domain.Arbol;
import java.util.List;

public interface ArbolService {

    // Se obtiene la lista de árboles, filtrando por el estado activo si se desea
    public List<Arbol> getArboles(boolean activo);

    // Se obtiene un árbol a partir del id del árbol
    public Arbol getArbol(Arbol arbol);

    // Se inserta un nuevo árbol si el id está vacío
    // Se actualiza un árbol si el id NO está vacío
    public void save(Arbol arbol);

    // Se elimina el árbol que tiene el id pasado por parámetro
    public void delete(Arbol arbol);
}
