package com.innowise.userserialization.dao;

import com.innowise.userserialization.exception.DaoException;

import java.util.Optional;

public interface CrudDao<T> {

    void create(T entity) throws DaoException;

    Optional<T> read(Integer id) throws DaoException;

    void update(T entity) throws DaoException;

    void delete(Integer id) throws DaoException;
}
