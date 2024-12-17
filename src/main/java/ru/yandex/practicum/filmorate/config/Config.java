package ru.yandex.practicum.filmorate.config;

import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class Config {

    public static final int MAX_FILM_DESC_LEN = 200;
    public static final LocalDate FILM_RELEASE_MIN_DATE = LocalDate.of(1895, Month.DECEMBER, 28);

    public static List<Film> getDefaultFilms() {
        return new ArrayList<>();
    }

    public static List<User> getDefaultUsers() {
        return new ArrayList<>();
    }



}
