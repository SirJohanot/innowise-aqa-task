package com.innowise.userserialization.dao;

import java.sql.SQLException;
import java.util.Optional;

public interface CrudDao<T> {

    void create(T entity) throws SQLException;

    Optional<T> read(Integer id) throws SQLException;

    void update(T entity) throws SQLException;

    void delete(Integer id) throws SQLException;
}
