package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.Film;

public interface Storage<T> {

    void add(T t);

    T remove(int id);

    T update(int id, T t);

}
