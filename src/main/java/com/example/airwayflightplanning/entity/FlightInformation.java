package com.example.airwayflightplanning.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "flights")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FlightInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true,nullable = false)
    private long pnr;
    @Column(nullable = false)
    private long airlineCode;
    @Column(nullable = false)
    @Min(101)
    @Max(181)
    private long sourceAirportCode;
    @Column(nullable = false)
    @Min(101)
    @Max(181)
    private long destinationAirportCode;
    @Column(nullable = false)
    private Date date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightInformation that = (FlightInformation) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
