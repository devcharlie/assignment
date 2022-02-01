package com.test.artists.entities;

import com.test.artists.dto.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "ARTISTS")
@Getter
@Setter
public class Artist implements Serializable {

    private static final long serialVersionUID = 1L;

    public Artist() {
    }

    public Artist(String firstName, String middleName, String lastName, Category category, LocalDate birthday, String email, String notes) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.category = category;
        this.birthday = birthday;
        this.email = email;
        this.notes = notes;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotEmpty(message = "First name must not be empty")
    @Column(name = "FIRST_NAME", length = 50, nullable = false)
    private String firstName;

    @Column(name = "MIDDLE_NAME", length = 50)
    private String middleName;

    @NotEmpty(message = "Last name must not be empty")
    @Column(name = "LAST_NAME", length = 50, nullable = false)
    private String lastName;

    //    @Pattern(message = "Category must not be empty", regexp = "ACTOR|PAINTER|SCULPTOR")
    @Column(name = "CATEGORY", length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Past(message = "Birthday must not be empty")
    @Column(name = "BIRTHDAY", columnDefinition = "DATE", length = 50, nullable = false)
    private LocalDate birthday;

    @Email(message = "Email must be not empty and in correct email format")
    @Column(name = "EMAIL", length = 50, nullable = false, unique = true)
    private String email;

    @Column(name = "NOTES", length = 200)
    private String notes;
}
