package fr.challenge.filerouge_utopios.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity(name = "user_account")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class User extends AbstractEntity {
    @Column(nullable = false, unique = true)
    @NotNull(message = "You must enter an email")
    @NotBlank(message = "You must enter an email")
    @Email(message = "You must enter a valid email")
    private String email;

    @Column(nullable = false)
    @NotNull(message = "You must enter an username")
    @NotBlank(message = "You must enter a password")
    private String password;

    @Column(nullable = false, unique = true)
    @NotNull(message = "You must enter a pseudo")
    @NotBlank(message = "You must enter a pseudo")
    private String pseudo;

    @Column(nullable = false)
    @NotNull(message = "You must enter a birth date")
    private LocalDate birthDate;

    @Column(nullable = false)
    @Builder.Default
    private LocalDate registrationDate = LocalDate.now();

    @Column(nullable = false)
    @Builder.Default
    private int elo = 1000;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private AccountLevel accountLevel = AccountLevel.USER;

    @ManyToOne
    @JoinColumn(nullable = false, name = "country_id", referencedColumnName = "id")
    @NotNull(message = "You must enter a country")
    private Country country;

    public enum AccountLevel {
        ADMIN, USER
    }
}
