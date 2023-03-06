package com.example.show_time.Converters;

import com.example.show_time.Entity.Movie;
import com.example.show_time.EntryDtos.MovieEntryDto;

public class MovieConverter {

    public static Movie convertEntryDtoToEntity(MovieEntryDto movieEntryDto){

        Movie movie = Movie.builder()
                .movieName(movieEntryDto.getMovieName()).duration(movieEntryDto.getDuration())
                .genre(movieEntryDto.getGenre()).language(movieEntryDto.getLanguage()).ratings(movieEntryDto.getRatings()).build();

        return movie;
    }
}
