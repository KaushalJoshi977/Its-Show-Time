package com.example.show_time.Converters;

import com.example.show_time.Entity.Show;
import com.example.show_time.EntryDtos.ShowEntryDto;

public class ShowConverter {

    public static Show convertEntryToEntity(ShowEntryDto showEntryDto){

        Show show = Show.builder()
                .showDate(showEntryDto.getLocalDate())
                .showTime(showEntryDto.getLocalTime())
                .showType(showEntryDto.getShowType())
                .build();

        return show;
    }
}
