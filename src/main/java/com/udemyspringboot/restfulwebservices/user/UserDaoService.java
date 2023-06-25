package com.udemyspringboot.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();

    private static int usersCount = 3;

    static {
        users.add(new User(1, "John", LocalDate.now().minusYears(30)));
        users.add(new User(2, "Dani", LocalDate.now().minusYears(25)));
        users.add(new User(3, "Robert", LocalDate.now().minusYears(45)));
    }

    public List<User> findAllUsers(){
        return users;
    }

    public User findUserById(Integer id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
    }

    public User createUser(User user) {
        user.setId(++usersCount);
        users.add(user);
        return  user;
    }

    public void deleteUserById(Integer id) {
        users.removeIf(user -> user.getId().equals(id));
    }
}
