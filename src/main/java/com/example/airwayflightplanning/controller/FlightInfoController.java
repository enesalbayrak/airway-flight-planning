package com.example.airwayflightplanning.controller;

import com.example.airwayflightplanning.dto.request.AddFlightInfoRequest;
import com.example.airwayflightplanning.dto.request.UpdateFlightInfoRequest;
import com.example.airwayflightplanning.dto.response.FlightInfoResponse;
import com.example.airwayflightplanning.service.FlightInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/flights")
@RequiredArgsConstructor
public class FlightInfoController {

    private final FlightInfoService flightInfoService;

    @PostMapping
    public FlightInfoResponse addFlight(@RequestBody AddFlightInfoRequest addFlightInfoRequest){
        return flightInfoService.save(addFlightInfoRequest);
    }

    @GetMapping("/getByAirlineCode")
    public List<FlightInfoResponse> getByAirlineCode (@RequestParam long airlineCode){
        return  flightInfoService.getByAirlineCode(airlineCode);
    }

    @GetMapping("/getBySourceAirportCode")
    public List<FlightInfoResponse> getBySourceAirportCode(@RequestParam long sourceAirportCode){
        return flightInfoService.getBySourceAirportCode(sourceAirportCode);
    }

    @GetMapping("/getByDestinationAirportCode")
    public List<FlightInfoResponse> getByDestinationAirportCode(@RequestParam long destinationAirportCode){
        return flightInfoService.getByDestinationCode(destinationAirportCode);
    }

    @PostMapping("/getByDate")
    public List<FlightInfoResponse> getByDate (@RequestBody LocalDate date){
        return flightInfoService.getByDate(date);
    }

    @PutMapping()
    public FlightInfoResponse updateFlight (@RequestBody UpdateFlightInfoRequest updateFlightInfoRequest){
        return flightInfoService.updateFlight(updateFlightInfoRequest);
    }

    @DeleteMapping("{pnr}")
    public void deleteFlight (@PathVariable long pnr){
         flightInfoService.deleteFlight(pnr);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handle(Exception exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

}
