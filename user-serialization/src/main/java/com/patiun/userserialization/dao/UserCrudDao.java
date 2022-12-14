package com.patiun.userserialization.dao;

import com.patiun.userserialization.entity.UserDto;
import com.patiun.userserialization.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserCrudDao implements CrudDao<UserDto> {

    private static final String CREATE_QUERY = "INSERT INTO user SET first_name = ?, last_name = ?, email = ?, password = MD5(?), birthday = ? ;";
    private static final String UPDATE_QUERY = "UPDATE user SET first_name = ?, last_name = ?, email = ?, password = MD5(?), birthday = DATE(?) WHERE id = ? ;";
    private static final String SELECT_QUERY = "SELECT * FROM user WHERE id = ? ;";
    private static final String DELETE_QUERY = "DELETE FROM user WHERE id = ? ;";

    private final Connection connection;

    public UserCrudDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(UserDto entity) throws DaoException {
        try (PreparedStatement preparedStatement = buildPreparedStatementWithUserFields(CREATE_QUERY, entity)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<UserDto> read(Integer id) throws DaoException {
        try (PreparedStatement preparedStatement = buildPreparedStatement(SELECT_QUERY, id)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<UserDto> results = extractResultsFromResultSet(resultSet);
            if (results.size() >= 1) {
                UserDto result = results.get(0);
                return Optional.of(result);
            } else {
                return Optional.empty();
            }
        } catch (SQLException | ParseException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(UserDto entity) throws DaoException {
        try (PreparedStatement preparedStatement = buildPreparedStatementWithUserFields(UPDATE_QUERY, entity)) {
            Integer id = entity.getId();
            preparedStatement.setObject(6, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Integer id) throws DaoException {
        try (PreparedStatement preparedStatement = buildPreparedStatement(DELETE_QUERY, id)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private PreparedStatement buildPreparedStatementWithUserFields(String query, UserDto userDto) throws SQLException {
        String firstName = userDto.getFirstName();
        String lastName = userDto.getLastName();
        String email = userDto.getEmail();
        String password = userDto.getPassword();
        LocalDate birthday = userDto.getBirthday();
        String birthdayLine = birthday.toString();
        return buildPreparedStatement(query, firstName, lastName, email, password, birthdayLine);
    }

    private PreparedStatement buildPreparedStatement(String query, Object... parameters) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        for (int i = 1; i <= parameters.length; i++) {
            preparedStatement.setObject(i, parameters[i - 1]);
        }
        return preparedStatement;
    }

    private List<UserDto> extractResultsFromResultSet(ResultSet resultSet) throws SQLException, ParseException {
        List<UserDto> entities = new ArrayList<>();
        while (resultSet.next()) {
            UserDto entity = mapResult(resultSet);
            entities.add(entity);
        }
        return entities;
    }

    private UserDto mapResult(ResultSet resultSet) throws SQLException, ParseException {
        Integer id = resultSet.getInt("id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        String birthday = resultSet.getString("birthday");
        return new UserDto(id, firstName, lastName, email, password, birthday);
    }
}
