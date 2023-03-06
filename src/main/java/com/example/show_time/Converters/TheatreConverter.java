package com.example.show_time.Converters;

import com.example.show_time.Entity.Theatre;
import com.example.show_time.EntryDtos.TheatreEntryDto;

public class TheatreConverter {

    public static Theatre convertDtoToEntity(TheatreEntryDto theaterEntryDto){

        return Theatre.builder().location(theaterEntryDto.getLocation())
                .name(theaterEntryDto.getName()).build();

    }
}
