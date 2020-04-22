package org.example.dao;

import org.example.model.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityDaoImpl {

    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/world?&autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Europe/Berlin";
    private static final String USER_NAME = "root";
    private static final String USER_PASSWORD = "root";

    private static final String GET_ALL_CITIES = "SELECT * FROM city";
    private static final String GET_ALL_FROM_DISTRICT = "SELECT * FROM city WHERE District LIKE ?";//prepend statement

    public List<City> getAll() {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        List<City> cityList = new ArrayList();

        try {

            connection = DriverManager.getConnection(CONNECTION_STRING, USER_NAME, USER_PASSWORD);

            statement = connection.createStatement();

            resultSet = statement.executeQuery(GET_ALL_CITIES);

            while (resultSet.next()) {
                cityList.add(
                        new City(
                                resultSet.getInt("ID"),
                                resultSet.getString("name"),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getInt(5)
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return cityList;

    }

    public List<City> getFromDistrict(String district) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<City> cityList = new ArrayList();

        try {

            connection = DriverManager.getConnection(CONNECTION_STRING, USER_NAME, USER_PASSWORD);

            preparedStatement = connection.prepareStatement(GET_ALL_FROM_DISTRICT);

            preparedStatement.setString(1, district + '%');

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                cityList.add(
                        new City(
                                resultSet.getInt("ID"),
                                resultSet.getString("name"),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getInt(5)
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return cityList;

    }


}
