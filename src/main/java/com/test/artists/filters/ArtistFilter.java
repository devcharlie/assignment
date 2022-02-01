package com.test.artists.filters;

import com.test.artists.dto.Category;
import com.test.artists.entities.Artist;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Slf4j
public class ArtistFilter {

    public static Predicate<Artist> partialFirstNameMatch(String partialFirstName) {
        return p -> p.getFirstName().toLowerCase().contains(partialFirstName.toLowerCase());
    }

    public static Predicate<Artist> partialLastNameMatch(String partialLastName) {
        return p -> p.getLastName().toLowerCase().contains(partialLastName.toLowerCase());
    }

    public static Predicate<Artist> categoryMatch(Category category) {
        return p -> p.getCategory().equals(category);
    }

    public static Predicate<Artist> BirthdayMonthMatch(Integer birthdayMonth) {
        return p -> p.getBirthday().getMonth().getValue() == birthdayMonth + 1;
    }

    public static List<Artist> filterArtists(List<Artist> artists, String firstName, String lastName,
                                             Category category, Integer birthdayMonth) {
        log.info("Starting to filter artist list of size: {}", artists.size());
        List<Artist> filteredList = artists.parallelStream()
                .filter(partialFirstNameMatch(firstName))
                .filter(partialLastNameMatch(lastName))
                .filter(categoryMatch(category))
                .filter(BirthdayMonthMatch(birthdayMonth))
                .collect(Collectors.toList());
        log.info("Artist list filtered down to {} artists", filteredList.size());
        return filteredList;
    }
}
