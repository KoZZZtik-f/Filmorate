package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;

public interface Storage<T> {

    T get(int id);

    boolean create(T t);

    T remove(int id);

    T update(T t);

    Collection<T> getAll();

    int size();

    boolean containsId(int id);

}
