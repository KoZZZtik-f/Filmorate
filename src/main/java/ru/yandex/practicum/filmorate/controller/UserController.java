package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.config.Config;
import ru.yandex.practicum.filmorate.exception.UserNotFoundException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

import static ru.yandex.practicum.filmorate.validator.Validator.validateUser;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    List<User> users = Config.getDefaultUsers();

    @PostMapping
    public User createUser(@RequestBody User user) {
        validateUser(user);
        if (user.getName().isBlank()) {
            user.setName(user.getLogin());
        }
        users.add(user);
        log.info("User added: {} {}", user.getLogin(), user.getEmail());
        return user;
    }

    @PutMapping
    public User updateUser(@RequestBody User newUser) {
        validateUser(newUser);
        for (int i = 0; i < users.size(); i++) {
            var oldUser = users.get(i);
            if (oldUser.getId() == newUser.getId()) {
                users.remove(i); // remove oldUser
                users.add(newUser);
                log.info("User updated: ({} {}) -> ({} {})", oldUser.getName(), oldUser.getEmail(),
                        newUser.getName(), newUser.getEmail());
                return oldUser;
            }
        }
        throw new UserNotFoundException();
    }

    @GetMapping
    public List<User> getAllUsers() {
        return users;
    }

}
