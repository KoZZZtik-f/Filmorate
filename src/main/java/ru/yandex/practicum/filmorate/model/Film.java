package ru.yandex.practicum.filmorate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Set;

@Data
public class Film extends idObject {

    private String name;
    private String description;
    private LocalDate releaseDate;
    private Duration duration;
    @JsonIgnore
    private Set<Integer> likes;

}
