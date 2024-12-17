package ru.yandex.practicum.filmorate.validator;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.config.Config;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import java.time.Instant;
import java.time.LocalDate;

@Slf4j
public final class Validator {

    public static void validateFilm(Film film) {
        if (film.getName().isBlank()
                || film.getDescription().length() > Config.MAX_FILM_DESC_LEN
                || film.getReleaseDate().isBefore(Config.FILM_RELEASE_MIN_DATE)
                || film.getDuration().isNegative()) {
            log.debug("Film {} {} has not been validated {}",film.getName(), film.getReleaseDate(), film);
            throw new ValidationException();
        }
    }

    public static void validateUser(User user) {
        if (user.getEmail().isBlank() || !user.getEmail().contains("@")
                || user.getLogin().isBlank() || user.getLogin().contains(" ")
                || user.getBirthday().isAfter(LocalDate.now())) {
            log.debug("User {} {} has not been validated {}", user.getLogin(), user.getEmail(), user);
            throw new ValidationException();
        }
    }

}
