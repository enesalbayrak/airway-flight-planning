package com.example.airwayflightplanning.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
@Data
@AllArgsConstructor
public class UpdateFlightInfoRequest {
    private long pnr;
    private long airlineCode;
    private long sourceAirportCode;
    private long destinationAirportCode;
    private Date date;
}
