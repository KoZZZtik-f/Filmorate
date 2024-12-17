package ru.yandex.practicum.filmorate.storage.userstorage;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.FilmNotFoundException;
import ru.yandex.practicum.filmorate.exception.UserNotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.InMemoryStorage;

@Component
public class InMemoryUserStorage extends InMemoryStorage<User> {

    @Override
    public void add(User film) {
        super.list.add(film);
    }

    @Override
    public User remove(int id) {
        for (int i = 0; i < super.list.size(); i++) {
            var curUser = super.list.get(i);
            if (curUser.getId() == id) {
                return super.list.remove(i);
            }
        }
        throw new UserNotFoundException();
    }

    @Override
    public User update(int id, User film) {
        for (int i = 0; i < super.list.size(); i++) {
            var curUser = super.list.get(i);
            if (curUser.getId() == id) {
                super.list.remove(i);
                super.list.add(film);
                return curUser;
            }
        }
        throw new UserNotFoundException();
    }

}
