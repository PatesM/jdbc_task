package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Ivan", "Ivanov", (byte) 23);
        userService.saveUser("Sergey", "Sergeev", (byte) 30);
        userService.saveUser("Oleg", "Popov", (byte) 29);
        userService.saveUser("Irina", "Nikolaeva", (byte) 35);

        List<User> users = userService.getAllUsers();
        System.out.print(users);

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
