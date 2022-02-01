package com.test.artists.controller;

import com.test.artists.dto.Category;
import com.test.artists.entities.Artist;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
public class ArtistControllerTests {

    @Autowired
    private ArtistController artistController;

    @Test
    void validationTestShouldReturnErrorMessageForFirstNameLastNameCategoryBirthdayEmail() {
        Artist invalidArtist = new Artist("", "", null, Category.ACTOR, LocalDate.ofYearDay(2025, 1), "fakeemail", null);

        ConstraintViolationException exception = assertThrows(ConstraintViolationException.class, () -> {
            artistController.createArtist(invalidArtist);
        });
        assertEquals(4, exception.getConstraintViolations().size());
    }
}
