package com.example.airwayflightplanning.repository;

import com.example.airwayflightplanning.entity.FlightInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface FlightInfoRepository extends JpaRepository<FlightInformation,Long> {

    List<FlightInformation>getAllByAirlineCode(long airlineCode);
    List<FlightInformation>getAllBySourceAirportCode(long sourceAirportCode);
    List<FlightInformation>getAllByDestinationAirportCode(long destinationAirportCode);
    List<FlightInformation>getAllByDate(Date date);
    FlightInformation findByPnr(long pnr);
    void deleteAllByPnr(long pnr);
    List<FlightInformation> findAllByDateGreaterThanAndDateLessThan(Date startDate,Date endDate);
    @Query(nativeQuery = true,value = "SELECT * from flights where date like %?1%")
    List<FlightInformation> findAllByDateLike(String date);
}
