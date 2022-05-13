package com.example.airwayflightplanning.service;

import com.example.airwayflightplanning.dto.request.AddFlightInfoRequest;
import com.example.airwayflightplanning.dto.request.UpdateFlightInfoRequest;
import com.example.airwayflightplanning.dto.response.FlightInfoResponse;

import java.util.Date;
import java.util.List;

public interface FlightInfoService {
    FlightInfoResponse save(AddFlightInfoRequest addFlightInfoRequest);

    List<FlightInfoResponse> getByAirlineCode(long airlineCode);

    List<FlightInfoResponse> getBySourceAirportCode(long sourceAirportCode);

    List<FlightInfoResponse> getByDestinationCode(long destinationAirportCode);

    List<FlightInfoResponse> getByDate(Date date);

    FlightInfoResponse updateFlight(UpdateFlightInfoRequest updateFlightInfoRequest);

    void deleteFlight(long pnr);
}
