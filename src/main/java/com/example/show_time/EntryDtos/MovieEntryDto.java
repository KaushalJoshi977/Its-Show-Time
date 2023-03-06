package com.example.show_time.EntryDtos;

import com.example.show_time.Enums.Genre;
import com.example.show_time.Enums.Language;
import lombok.Data;

@Data
public class MovieEntryDto {

    private String movieName;

    private double ratings;

    private int duration;

    private Language language;


    private Genre genre;
}
