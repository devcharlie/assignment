package com.test.artists.controller;

import com.test.artists.filters.ArtistFilter;
import com.test.artists.entities.Artist;
import com.test.artists.dto.Category;
import com.test.artists.repositories.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/artists", produces = MediaType.APPLICATION_JSON_VALUE)
public class ArtistController {

    @Autowired
    private ArtistRepository artistRepository;

    @GetMapping("/{id}")
    public Optional<Artist> getArtist(@PathVariable Long id) {
        return artistRepository.findById(id);
    }

    @GetMapping("/")
    public List<Artist> getArtists(
            @RequestParam(required = false, defaultValue = "") String firstName,
            @RequestParam(required = false, defaultValue = "") String lastName,
            @RequestParam(required = false) Category category,
            @RequestParam(required = false) Integer birthdayMonth
    ) {
        List<Artist> artists;
        if(firstName.isEmpty() && lastName.isEmpty() && category == null && birthdayMonth == null){
            artists = artistRepository.findAll();
        } else {
            artists = ArtistFilter.filterArtists(artistRepository.findAll(), firstName, lastName, category, birthdayMonth);
        }
        return artists;
    }

    @PostMapping("/")
    public void createArtist(@Valid @RequestBody Artist artist) {
        artistRepository.save(artist);
    }

    @DeleteMapping("{id}")
    public void deleteArtist(@PathVariable Long id) {
        artistRepository.deleteById(id);
    }
}
