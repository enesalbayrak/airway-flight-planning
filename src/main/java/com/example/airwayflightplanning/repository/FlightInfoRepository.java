package com.example.airwayflightplanning.repository;

import com.example.airwayflightplanning.entity.FlightInformation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface FlightInfoRepository extends JpaRepository<FlightInformation,Long> {

    List<FlightInformation>getAllByAirlineCode(long airlineCode);
    List<FlightInformation>getAllBySourceAirportCode(long sourceAirportCode);
    List<FlightInformation>getAllByDestinationAirportCode(long destinationAirportCode);
    List<FlightInformation>getAllByDate(Date date);
}
