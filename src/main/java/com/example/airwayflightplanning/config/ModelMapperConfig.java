package com.example.airwayflightplanning.config;

import com.example.airwayflightplanning.dto.response.FlightInfoResponse;
import com.example.airwayflightplanning.entity.FlightInformation;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    private static final Converter<FlightInformation, FlightInfoResponse>
            FLIGHT_INFORMATION_FLIGHT_INFO_RESPONSE_CONVERTER =
            (context) -> {
                var flightInfoResponse = new FlightInfoResponse();
                var flightInformation = context.getSource();
                flightInfoResponse.setPnr(flightInformation.getPnr());
                flightInfoResponse.setAirlineCode(flightInformation.getAirlineCode());
                flightInfoResponse.setDestinationAirportCode(flightInformation.getDestinationAirportCode());
                flightInfoResponse.setSourceAirportCode(flightInformation.getSourceAirportCode());
                flightInfoResponse.setDate(flightInformation.getDate());

                return flightInfoResponse;
    };

    @Bean
     ModelMapper createModelMapper() {
        var modelMapper = new ModelMapper();
            modelMapper.addConverter(FLIGHT_INFORMATION_FLIGHT_INFO_RESPONSE_CONVERTER,
                FlightInformation.class,FlightInfoResponse.class);
        return modelMapper;
    }

}
