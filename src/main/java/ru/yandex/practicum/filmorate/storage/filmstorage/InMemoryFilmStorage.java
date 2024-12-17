package ru.yandex.practicum.filmorate.storage.filmstorage;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.filmorate.config.Config;
import ru.yandex.practicum.filmorate.exception.FilmNotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.InMemoryStorage;

import java.util.List;

import static ru.yandex.practicum.filmorate.validator.Validator.validateFilm;

@Component
public class InMemoryFilmStorage extends InMemoryStorage<Film> {

    @Override
    public void add(Film film) {
        super.list.add(film);
    }

    @Override
    public Film remove(int id) {
        for (int i = 0; i < super.list.size(); i++) {
            var curFilm = super.list.get(i);
            if (curFilm.getId() == id) {
                return super.list.remove(i);
            }
        }
        throw new FilmNotFoundException();
    }

    @Override
    public Film update(int id, Film film) {
        for (int i = 0; i < super.list.size(); i++) {
            var curFilm = super.list.get(i);
            if (curFilm.getId() == id) {
                super.list.remove(i);
                super.list.add(film);
                return curFilm;
            }
        }
        throw new FilmNotFoundException();
    }

}
