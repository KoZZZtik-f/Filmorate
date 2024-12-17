package ru.yandex.practicum.filmorate.storage;

import java.util.List;

public abstract class InMemoryStorage<T> implements Storage<T> {

    protected List<T> list;


    public abstract void add(T t);

    public abstract T remove(int id);

    public abstract T update(int id, T film);


}
