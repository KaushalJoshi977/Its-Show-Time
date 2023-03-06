package com.example.show_time.Services;

import com.example.show_time.Converters.MovieConverter;
import com.example.show_time.Entity.Movie;
import com.example.show_time.EntryDtos.MovieEntryDto;
import com.example.show_time.Repositories.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    MovieRepo movieRepo;

    public String addMovie(MovieEntryDto movieEntryDto){

        Movie movie = MovieConverter.convertEntryDtoToEntity(movieEntryDto);
        movieRepo.save(movie);
        return "Movie Added Successfully";

    }
}
