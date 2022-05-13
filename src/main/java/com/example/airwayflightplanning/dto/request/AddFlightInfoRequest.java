package com.example.airwayflightplanning.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddFlightInfoRequest {
    private long pnr;
    private long airlineCode;
    private long sourceAirportCode;
    private long destinationAirportCode;
    private  Date date;
}
