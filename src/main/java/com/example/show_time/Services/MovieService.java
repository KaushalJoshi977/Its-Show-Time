package com.example.show_time.Services;

import com.example.show_time.Converters.MovieConverter;
import com.example.show_time.Entity.Movie;
import com.example.show_time.EntryDtos.MovieEntryDto;
import com.example.show_time.Repositories.MovieRepo;
import com.example.show_time.ResponseDto.MovieResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    MovieRepo movieRepo;

    public String addMovie(MovieEntryDto movieEntryDto){

        Movie movie = MovieConverter.convertEntryDtoToEntity(movieEntryDto);
        movieRepo.save(movie);
        return "Movie Added Successfully";

    }

    public List<MovieResponseDto> movieList(){
        List<Movie> movieList = movieRepo.findAll();
        List<MovieResponseDto> movieResponseDtoList = new ArrayList<>();
        for (Movie movie:movieList) {
            MovieResponseDto movieResponseDto = MovieResponseDto.builder().movieName(movie.getMovieName())
                    .language(movie.getLanguage()).genre(movie.getGenre()).duration(movie.getDuration())
                    .ratings(movie.getRatings()).build();
            movieResponseDtoList.add(movieResponseDto);
        }
        return movieResponseDtoList;
    }
}
