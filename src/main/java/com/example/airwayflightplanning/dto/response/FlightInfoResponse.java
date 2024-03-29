package com.example.airwayflightplanning.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightInfoResponse {
    private long pnr;
    private long airlineCode;
    private long sourceAirportCode;
    private long destinationAirportCode;
    private Date date;
}
