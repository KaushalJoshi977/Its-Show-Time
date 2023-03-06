package com.example.show_time.Services;

import com.example.show_time.Converters.ShowConverter;
import com.example.show_time.Entity.*;
import com.example.show_time.EntryDtos.ShowEntryDto;
import com.example.show_time.Enums.SeatType;
import com.example.show_time.Repositories.MovieRepo;
import com.example.show_time.Repositories.ShowRepo;
import com.example.show_time.Repositories.TheatreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {

    @Autowired
    MovieRepo movieRepo;

    @Autowired
    TheatreRepo theatreRepo;

    @Autowired
    ShowRepo showRepo;

    public String addShow(ShowEntryDto showEntryDto)
    {
        //1. Create a show
        Show show = ShowConverter.convertEntryToEntity(showEntryDto);

        int movieId = showEntryDto.getMovieId();
        int theaterId = showEntryDto.getTheaterId();

        Movie movie = movieRepo.findById(movieId).get();
        Theatre theatre = theatreRepo.findById(theaterId).get();


        //Setting the attribute of foreignKey
        show.setMovie(movie);
        show.setTheatre(theatre);

        //Pending attributes the listOfShowSeatsEnity

        List<ShowSeat> showSeats = createShowSeatEntity(showEntryDto,show);

        show.setListOfShowSeats(showSeats);


        //Now we  also need to update the parent entities


        show = showRepo.save(show);

        movie.getShowList().add(show);
        theatre.getShowList().add(show);


        movieRepo.save(movie);

        theatreRepo.save(theatre);

        return "The show has been added successfully";

    }

    private List<ShowSeat> createShowSeatEntity(ShowEntryDto showEntryDto,Show show){



        //Creating the ShowSeatEntity
        //We need to set its attribute

        Theatre theatre = show.getTheatre();

        List<TheatreSeat> theaterSeatList = theatre.getTheatreSeats();

        List<ShowSeat> showSeats = new ArrayList<>();

        for(TheatreSeat theaterSeatEntity : theaterSeatList){

            ShowSeat showSeatEntity = new ShowSeat();

            showSeatEntity.setSeatNo(theaterSeatEntity.getSeatNo());
            showSeatEntity.setSeatType(theaterSeatEntity.getSeatType());

            if(theaterSeatEntity.getSeatType().equals(SeatType.CLASSIC))
                showSeatEntity.setPrice(showEntryDto.getClassicSeatPrice());

            else
                showSeatEntity.setPrice(showEntryDto.getPremiumSeatPrice());

            showSeatEntity.setBooked(false);
            showSeatEntity.setShow(show); //parent : foreign key for the showSeat Entity

            showSeats.add(showSeatEntity); //Adding it to the list
        }

        return  showSeats;

    }
}
