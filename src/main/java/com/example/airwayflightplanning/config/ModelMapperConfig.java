package com.example.airwayflightplanning.config;

import com.example.airwayflightplanning.dto.request.UpdateFlightInfoRequest;
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
    private static final Converter<UpdateFlightInfoRequest,FlightInformation> UPDATE_FLIGHT_INFO_REQUEST_FLIGHT_INFORMATION_CONVERTER =
            context -> {
        var updateflight = context.getSource();
        var flightinfo = new FlightInformation();
        flightinfo.setAirlineCode(updateflight.getAirlineCode());
        flightinfo.setSourceAirportCode(updateflight.getSourceAirportCode());
        flightinfo.setDestinationAirportCode(updateflight.getDestinationAirportCode());
        flightinfo.setDate(updateflight.getDate());
        return flightinfo;
    };


    @Bean
     ModelMapper createModelMapper() {
        var modelMapper = new ModelMapper();
            modelMapper.addConverter(FLIGHT_INFORMATION_FLIGHT_INFO_RESPONSE_CONVERTER,
                FlightInformation.class,FlightInfoResponse.class);
            modelMapper.addConverter(UPDATE_FLIGHT_INFO_REQUEST_FLIGHT_INFORMATION_CONVERTER,UpdateFlightInfoRequest.class,FlightInformation.class);
        return modelMapper;
    }

}
