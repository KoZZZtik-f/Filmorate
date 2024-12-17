package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.config.Config;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.validator.Validator;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;

@SpringBootTest
public class ValidatorTests {

    LocalDate randomDateOf1999 = LocalDate.of(1999, Month.DECEMBER, 15);
    Duration durationBetween1And2Hours = Duration.ofMinutes(76);
    Duration positiveDuration = Duration.ofMinutes(1);
    Duration negativeDuration = Duration.ofMinutes(-1);


    User validUser;
    Film validFilm;

    private static final String EMPTY_STR = "";
    private static final String BLANK_STR = "            ";
    private static final String VALID_DESC = "description";


    @BeforeEach
    void beforeEach() {

        validUser = new User();
        validUser.setName("name");
        validUser.setId(0);
        validUser.setLogin("Login");
        validUser.setBirthday(randomDateOf1999); // random 1999 date
        validUser.setEmail("kv.sobolev06@gmail.com");

        validFilm = new Film();
        validFilm.setName("Name"); // empty
        validFilm.setDescription(VALID_DESC);
        validFilm.setId(0);
        validFilm.setReleaseDate(randomDateOf1999); // random 1999 date
        validFilm.setDuration(durationBetween1And2Hours);

    }

    @Test
    void shouldNotThrowValidUser() {
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                Validator.validateUser(validUser);
            }
        });
    }

    @Test
    void shouldThrowInvalidEmail() {
        validUser.setEmail("kv.sobolev06?gmail.com"); // "@" is missing

        Assertions.assertThrows(ValidationException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Validator.validateUser(validUser);
            }
        });
    }
    @Test
    void shouldThrowBlankLogin() {
        validUser.setLogin(BLANK_STR);

        Assertions.assertThrows(ValidationException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Validator.validateUser(validUser);
            }
        });
    }

    @Test
    void shouldThrowEmptyLogin() {
        validUser.setLogin(EMPTY_STR);

        Assertions.assertThrows(ValidationException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Validator.validateUser(validUser);
            }
        });
    }

    // Films
    @Test
    void shouldNotThrowWhenFilmValid() {
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                Validator.validateUser(validUser);
            }
        });
    }

    @Test
    void shouldThrowWhenFilmEmptyName() {
        validFilm.setName(EMPTY_STR);

        Assertions.assertThrows(ValidationException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Validator.validateFilm(validFilm);
            }
        });
    }

    @Test
    void shouldThrowWhenFilmDescriptionLongerThanMaxLength() {
        StringBuilder descriptionBuilder = new StringBuilder();
        int maxDescLenPlus1 = Config.MAX_FILM_DESC_LEN + 1;

        for (int i = 0; i < maxDescLenPlus1; i++) {
            descriptionBuilder.append('a'); // add one char
        }
        validFilm.setDescription(descriptionBuilder.toString());

        Assertions.assertThrows(ValidationException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Validator.validateFilm(validFilm);
            }
        });
    }

    @Test
    void shouldNotThrowWhenFilmDescriptionShorterThanMaxLength() {
        StringBuilder descriptionBuilder = new StringBuilder();
        int maxDescLen = Config.MAX_FILM_DESC_LEN;

        for (int i = 0; i < maxDescLen; i++) {
            descriptionBuilder.append('a'); // add one char
        }
        validFilm.setDescription(descriptionBuilder.toString());

        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                Validator.validateFilm(validFilm);
            }
        });
    }

    @Test
    void shouldNotThrowWhenFilmReleaseAfterMinDate() {
        LocalDate minDateValid = Config.FILM_RELEASE_MIN_DATE;

        validFilm.setReleaseDate(minDateValid);

        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                Validator.validateFilm(validFilm);
            }
        });
    }

    @Test
    void shouldThrowWhenFilmReleaseBeforeMinDate() {
        LocalDate minDateMinus1Day = Config.FILM_RELEASE_MIN_DATE.minusDays(1);

        validFilm.setReleaseDate(minDateMinus1Day);

        Assertions.assertThrows(ValidationException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Validator.validateFilm(validFilm);
            }
        });
    }

    @Test
    void shouldNotThrowWhenFilmDurationIsPositive() {

        validFilm.setDuration(positiveDuration);

        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                Validator.validateFilm(validFilm);
            }
        });
    }

    @Test
    void shouldThrowWhenFilmDurationIsNegative() {

        validFilm.setDuration(negativeDuration);

        Assertions.assertThrows(ValidationException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Validator.validateFilm(validFilm);
            }
        });
    }






















}
