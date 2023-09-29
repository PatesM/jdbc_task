package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import lombok.extern.java.Log;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log
public class UserDaoJDBCImpl implements UserDao {

    Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() {
        String createQuery = """
                create table users (ID int auto_increment primary key,
                                    Name varchar(45) null,
                                    Last_Name varchar(45) null,
                                    Age tinyint null);
                """;
        try (Statement statement = connection.createStatement()) {
            if (statement.execute(""));
            statement.execute(createQuery);
            log.info("Таблица создана.\n");
        } catch (SQLException e) {
            log.severe("SQL ERROR!\n" + e.getMessage());
        }
    }

    @Override
    public void dropUsersTable() {
        String dropQuery = "DROP TABLE Users;";
        try (Statement statement = connection.createStatement()) {
            statement.execute(dropQuery);
            log.info("Таблица удалена.\n");
        } catch (SQLException e) {
            log.severe("SQL ERROR!\n" + e.getMessage());
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String saveQuery = "INSERT INTO Users (Name, Last_Name, Age) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(saveQuery)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            log.info("Пользователь сохранен.\n");
        } catch (SQLException e) {
            log.severe("SQL ERROR!\n" + e.getMessage());
        }
    }

    @Override
    public void removeUserById(long id) {
        String removeQuery = "DELETE FROM Users WHERE ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(removeQuery)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            log.info("Пользователь удален.\n");
        } catch (SQLException e) {
            log.severe("SQL ERROR!\n" + e.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();
        String selectQuery = "SELECT * FROM Users;";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(selectQuery);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("Name"));
                user.setLastName(resultSet.getString("Last_Name"));
                user.setAge(resultSet.getByte("Age"));
                userList.add(user);
            }
            log.info("Список пользователей получен.\n");
        } catch (SQLException e) {
            log.severe("SQL ERROR!\n" + e.getMessage());
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        String truncateQuery = "TRUNCATE TABLE Users";
        try (Statement statement = connection.createStatement()) {
            statement.execute(truncateQuery);
            log.info("Таблица очищена.\n");
        } catch (SQLException e) {
            log.severe("SQL ERROR!\n" + e.getMessage());
        }
    }
}
