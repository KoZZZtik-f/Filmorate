package ru.yandex.practicum.filmorate.storage.filmstorage;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.FilmNotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.InMemoryStorage;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.Collection;
import java.util.HashMap;

@Component
public class InMemoryFilmStorage extends InMemoryStorage<Film> implements FilmStorage {

    public InMemoryFilmStorage() {
        map = new HashMap<>();
    }



}
