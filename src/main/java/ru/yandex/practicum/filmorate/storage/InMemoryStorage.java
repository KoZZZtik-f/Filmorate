package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.config.Config;
import ru.yandex.practicum.filmorate.exception.FilmNotFoundException;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

public abstract class InMemoryStorage<T> implements Storage<T> {

    protected List<T> list;


    public abstract void add(T t);

    public abstract T remove(int id);

    public abstract T update(int id, T film);


}
