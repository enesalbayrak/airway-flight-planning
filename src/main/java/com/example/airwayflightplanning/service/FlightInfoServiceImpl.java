package com.example.airwayflightplanning.service;

import com.example.airwayflightplanning.dto.request.AddFlightInfoRequest;
import com.example.airwayflightplanning.dto.response.FlightInfoResponse;
import com.example.airwayflightplanning.entity.FlightInformation;
import com.example.airwayflightplanning.repository.FlightInfoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightInfoServiceImpl implements FlightInfoService{

    private final FlightInfoRepository flightInfoRepository;
    private final ModelMapper modelMapper;

    @Override
    public FlightInfoResponse save(AddFlightInfoRequest addFlightInfoRequest) {
        //LocalDate date = addFlightInfoRequest.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        //new Date(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss").format(date));
        var flightInformation = modelMapper.map(addFlightInfoRequest, FlightInformation.class);
        return modelMapper.map(flightInfoRepository.save(flightInformation),FlightInfoResponse.class);
    }

    @Override
    public List<FlightInfoResponse> getByAirlineCode(long airlineCode) {
        return flightInfoRepository.getAllByAirlineCode(airlineCode)
                .stream()
                .map(flightInformation -> modelMapper.map(flightInformation,FlightInfoResponse.class)).toList();
    }

    @Override
    public List<FlightInfoResponse> getBySourceAirportCode(long sourceAirportCode) {
        return flightInfoRepository.getAllBySourceAirportCode(sourceAirportCode)
                .stream().map(flightInformation -> modelMapper.map(flightInformation,FlightInfoResponse.class)).toList();
    }

    @Override
    public List<FlightInfoResponse> getByDestinationCode(long destinationAirportCode) {
        return flightInfoRepository.getAllByDestinationAirportCode(destinationAirportCode)
                .stream().map(flightInformation -> modelMapper.map(flightInformation,FlightInfoResponse.class)).toList();
    }

    @Override
    public List<FlightInfoResponse> getByDate(Date date) {
        return flightInfoRepository.getAllByDate(date)
                .stream().map(flightInformation -> modelMapper.map(flightInformation,FlightInfoResponse.class)).toList();
    }

    @Override
    public FlightInfoResponse updateFlight(long pnr) {
        return null;
    }

}
