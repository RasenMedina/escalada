package dao;

import java.util.List;

/**
 * Interfície genèrica DAO
 * Defineix les operacions bàsiques CRUD
 */
public interface DAO<T> {

    /**
     * Crear un nou registre
     */
    void create(T t) throws Exception;

    /**
     * Obtenir un element per ID
     */
    T getById(int id) throws Exception;

    /**
     * Obtenir tots els elements
     */
    List<T> getAll() throws Exception;

    /**
     * Actualitzar un element
     */
    void update(T t) throws Exception;

    /**
     * Eliminar un element
     */
    void delete(int id) throws Exception;
}
