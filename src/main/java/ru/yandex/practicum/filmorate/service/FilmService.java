package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.filmorate.exception.FilmNotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.filmstorage.FilmStorage;
import ru.yandex.practicum.filmorate.storage.filmstorage.InMemoryFilmStorage;

import java.util.*;

import static ru.yandex.practicum.filmorate.validator.Validator.validateFilm;

@Slf4j
@Service
public class FilmService {

    FilmStorage filmStorage;


    public FilmService(FilmStorage filmStorage) {
        this.filmStorage = filmStorage;
    }

    public Collection<Film> getAllFilms() {
        return filmStorage.getAll();
    }

    public Film createFilm(@RequestBody Film film) {
        validateFilm(film);
        film.setLikes(new HashSet<>());
        filmStorage.create(film);
        return film;
    }

    public Film getById(int id) {
        return filmStorage.get(id);
    }

    public Film update(Film newFilm) {
        validateFilm(newFilm);
        if (filmStorage.containsId(newFilm.getId())) {
            return filmStorage.update(newFilm);
        }

        throw new FilmNotFoundException();
    }

    public Film addLike(int id, int userId) {
        Film film = filmStorage.get(id);
        Set<Integer> likes = film.getLikes();
        if (likes.contains(userId)) {
            throw new RuntimeException("Пользователь уже ставил лайк на этот фильм");
        }
        likes.add(userId);
        return film;
    }

    public Film deleteLike(int id, int userId) {
        Film film = filmStorage.get(id);
        Set<Integer> likes = film.getLikes();
        if (likes.contains(userId)) {
            likes.remove(userId);
            return film;
        }
        throw new RuntimeException("Пользователь не ставил лайк на этот фильм");
    }

    public Collection<Film> getPopularFilms(Optional<Integer> count) {
        var films = Arrays.asList(filmStorage.getAll().toArray(new Film[0]));


        Collections.sort(films, new Comparator<Film>() {
            @Override
            public int compare(Film o1, Film o2) {
                return o2.getLikes().size() - o1.getLikes().size();
            }
        });
        int size = films.size();
        return films.subList(size - count.orElse(10), size);
    }

}
