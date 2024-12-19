package ru.yandex.practicum.filmorate.storage.userstorage;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.UserNotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.InMemoryStorage;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.Collection;
import java.util.HashMap;

@Component
public class InMemoryUserStorage extends InMemoryStorage<User> implements UserStorage {

    private int maxId = 0;

    public InMemoryUserStorage() {
        map = new HashMap<>();
    }

//    @Override
//    public boolean create(User obj) {
//        if (obj.getId() == null) {
//            obj.setId(currentId++);
//        }
//        if (map.containsKey(obj.getId())) {
//            throw new KeyAlreadyExistsException();
//        }
//        map.put(obj.getId(), obj);
//        return true;
//    }
//
//    @Override
//    public User remove(int id) {
//        return map.remove(id);
//    }
//
//    @Override
//    public User update(User user) {
//        if (!map.containsKey(user.getId())) {
//            throw new UserNotFoundException();
//        }
//        return map.put(user.getId(), user);
//    }
//
//    @Override
//    public Collection<User> getAll() {
//        return map.values();
//    }
//
//
//    @Override
//    public User get(int id) {
//        return map.get(id);
//    }



}
