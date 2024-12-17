package ru.yandex.practicum.filmorate.storage;

public interface Storage<T> {

    void add(T t);

    T remove(int id);

    T update(int id, T t);

}
