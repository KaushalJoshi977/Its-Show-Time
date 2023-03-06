package com.example.show_time.ResponseDto;

import com.example.show_time.Enums.Genre;
import com.example.show_time.Enums.Language;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieResponseDto {

    private String movieName;

    private double ratings;

    private int duration;

    private Language language;


    private Genre genre;
}
