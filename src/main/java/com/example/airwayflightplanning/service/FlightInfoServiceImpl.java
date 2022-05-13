package com.example.airwayflightplanning.service;

import com.example.airwayflightplanning.dto.request.AddFlightInfoRequest;
import com.example.airwayflightplanning.dto.request.UpdateFlightInfoRequest;
import com.example.airwayflightplanning.dto.response.FlightInfoResponse;
import com.example.airwayflightplanning.entity.FlightInformation;
import com.example.airwayflightplanning.repository.FlightInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.joda.time.DateTime;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightInfoServiceImpl implements FlightInfoService{

    private final FlightInfoRepository flightInfoRepository;
    private final ModelMapper modelMapper;

    @Override
    public FlightInfoResponse save(AddFlightInfoRequest addFlightInfoRequest) {
        if(addFlightInfoRequest.getSourceAirportCode()==addFlightInfoRequest.getDestinationAirportCode()){
            throw new IllegalArgumentException("It is not possible to have a flight to same airport");
        }
        var flightInformation = modelMapper.map(addFlightInfoRequest, FlightInformation.class);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(flightInformation.getDate());
        calendar.add(Calendar.DATE,-1);
        calendar.set(Calendar.HOUR,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        calendar.set(Calendar.MILLISECOND,999);
        Date dateBeforeDay= new Date();
        dateBeforeDay=calendar.getTime();
        //System.err.println(dateBeforeDay);
        calendar.add(Calendar.DATE,1);
        Date dateAfterDay = new Date();
        dateAfterDay = calendar.getTime();
        //System.err.println(dateAfterDay);
        var list = flightInfoRepository.findAllByDateGreaterThanAndDateLessThan(dateBeforeDay,dateAfterDay);
        //System.err.println(list);
        if(list.size()>2){
            throw new IllegalArgumentException("There are already three flights");
        }
        var savedFlight = flightInfoRepository.save(flightInformation);
        return modelMapper.map(savedFlight,FlightInfoResponse.class);
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
    public FlightInfoResponse updateFlight(UpdateFlightInfoRequest updateFlightInfoRequest) {
        var flightInfoFromDb = flightInfoRepository.findByPnr(updateFlightInfoRequest.getPnr());
        var flightInfoToUpdate = modelMapper.map(updateFlightInfoRequest,FlightInformation.class);
        flightInfoToUpdate.setId(flightInfoFromDb.getId());
        var savedFlightInfo = flightInfoRepository.save(flightInfoToUpdate);
        return modelMapper.map(savedFlightInfo,FlightInfoResponse.class);
    }

    @Override
    @Transactional
    public void deleteFlight(long pnr) {
        flightInfoRepository.deleteAllByPnr(pnr);
    }

}
