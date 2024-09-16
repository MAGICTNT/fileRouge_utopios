package fr.challenge.filerouge_utopios.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Tournament extends AbstractEntity {
    @Column(nullable = false)
    @NotNull(message = "You must enter a label")
    @NotBlank(message = "You must enter a label")
    private String label;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "You must enter a format")
    private Format format;

    @Column(nullable = false)
    @NotNull(message = "You must enter a start date")
    private LocalDate startDate;

    @Column(nullable = false)
    @NotNull(message = "You must enter an end date")
    private LocalDate endDate;

    @Column(nullable = false)
    @NotNull(message = "You must enter a status")
    @Enumerated(EnumType.STRING)
    private Status status;

    private int minAge;

    private int minElo;

    public enum Format {
        SINGLE, DOUBLE
    }

    public enum Status {
        NOT_STARTED, IN_PROGRESS, FINISHED
    }
}
