package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.UserNotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.userstorage.InMemoryUserStorage;
import ru.yandex.practicum.filmorate.storage.userstorage.UserStorage;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static ru.yandex.practicum.filmorate.validator.Validator.validateUser;

@Slf4j
@Service
public class UserService {

    UserStorage userStorage;


    public UserService(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public User createUser(User user) {
        validateUser(user);
        if (user.getName().isBlank()) {
            user.setName(user.getLogin()); //IP
        }
        userStorage.create(user);
        log.info("User added: {} {}", user.getLogin(), user.getEmail());
        return user;
    }

    public User getById(int id) {
        return userStorage.get(id);
    }

    public Collection<User> getAllUsers() {
        return userStorage.getAll();
    }

    public User updateUser(User newUser) {
        validateUser(newUser);
        for (int i = 0; i < userStorage.size(); i++) {
            var oldUser = userStorage.get(i);
            if (oldUser.getId() == newUser.getId()) {
                userStorage.remove(i); // remove oldUser
                userStorage.create(newUser);
                log.info("User updated: ({} {}) -> ({} {})", oldUser.getName(), oldUser.getEmail(),
                        newUser.getName(), newUser.getEmail());
                return oldUser;
            }
        }
        throw new UserNotFoundException();
    }



    public void addFriend(int id, int friendId) { // int id1, int id2
        User user = userStorage.get(id);
        User friend = userStorage.get(friendId);

        user.getFriends().add(friendId);
        friend.getFriends().add(id);
    }

    public void removeFriend(int id, int friendId) {
        User user = userStorage.get(id);
        User friend = userStorage.get(friendId);

        user.getFriends().remove(friendId);
        friend.getFriends().remove(id);
    }

    public Set<Integer> getMutualFriends(User user1, User user2) {
        Set<Integer> res = new HashSet<>(user1.getFriends());
        res.retainAll(user2.getFriends());
        return res;
    }

}
