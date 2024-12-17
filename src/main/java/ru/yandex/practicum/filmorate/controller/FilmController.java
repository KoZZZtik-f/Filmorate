package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.config.Config;
import ru.yandex.practicum.filmorate.exception.FilmNotFoundException;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

import static ru.yandex.practicum.filmorate.validator.Validator.validateFilm;

@Slf4j
@RestController
@RequestMapping("/films")
public class FilmController {

    List<Film> films = Config.getDefaultFilms();

    @PostMapping
    public Film addFilm(@RequestBody Film film) {
        validateFilm(film);
        films.add(film);
        log.info("Film added: {} {}", film.getName(), film.getReleaseDate().toString());
        return film;
    }

    @PutMapping
    public Film updateFilm(@RequestBody Film newFilm) {
        validateFilm(newFilm);
        for (int i = 0; i < films.size(); i++) {
            var oldFilm = films.get(i);
            if (oldFilm.getId() == newFilm.getId()) {
                films.remove(i); // remove oldFilm
                films.add(newFilm);
                log.info("Film updated: ({} {}) -> ({} {})", oldFilm.getName(), oldFilm.getReleaseDate().toString(),
                        newFilm.getName(), newFilm.getReleaseDate().toString());
                return oldFilm;
            }
        }
        throw new FilmNotFoundException();
    }

    @GetMapping
    public List<Film> getAllFilms() {
        return films;
    }

}
