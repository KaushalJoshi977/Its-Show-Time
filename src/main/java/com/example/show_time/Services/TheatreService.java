package com.example.show_time.Services;

import com.example.show_time.Converters.TheatreConverter;
import com.example.show_time.Entity.Theatre;
import com.example.show_time.Entity.TheatreSeat;
import com.example.show_time.EntryDtos.TheatreEntryDto;
import com.example.show_time.Enums.SeatType;
import com.example.show_time.Repositories.TheatreRepo;
import com.example.show_time.Repositories.TheatreSeatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheatreService {

    @Autowired
    TheatreSeatRepo theatreSeatRepository;


    @Autowired
    TheatreRepo theatreRepo;

    public String addTheatre(TheatreEntryDto theatreEntryDto)throws Exception{


        // validations :
        if(theatreEntryDto.getName()==null||theatreEntryDto.getLocation()==null){
            throw new Exception("Name and location should be valid");
        }

        Theatre theatre = TheatreConverter.convertDtoToEntity(theatreEntryDto);
        List<TheatreSeat> theatreSeatList = createTheatreSeats(theatreEntryDto,theatre);

        theatre.setTheatreSeats(theatreSeatList);
        theatreRepo.save(theatre);

        return "theatre Added successfully";
    }

    private List<TheatreSeat> createTheatreSeats(TheatreEntryDto theatreEntryDto,Theatre theatreEntity){

        int noClassicSeats = theatreEntryDto.getClassicSeatsCount();
        int noPremiumSeats = theatreEntryDto.getPremiumSeatsCount();

        List<TheatreSeat> theatreSeatList = new ArrayList<>();

        //Created the classic Seats
        for(int count = 1;count<=noClassicSeats;count++){

            //We need to make a new TheaterSeatEntity
            TheatreSeat theatreSeat = TheatreSeat.builder()
                    .seatType(SeatType.CLASSIC).seatNo(count+"C")
                    .theatre(theatreEntity).build();

            theatreSeatList.add(theatreSeat);
        }


        //Create the premium Seats
        for(int count=1;count<=noPremiumSeats;count++){

            TheatreSeat theatreSeat = TheatreSeat.builder().
                    seatType(SeatType.PREMIUM).seatNo(count+"P").theatre(theatreEntity).build();

            theatreSeatList.add(theatreSeat);
        }

        return theatreSeatList;

    }
}