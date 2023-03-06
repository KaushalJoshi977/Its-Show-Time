package com.example.show_time.EntryDtos;

import lombok.Data;

@Data
public class TheatreEntryDto {
    //Attributes that we require
    private String name;
    private String location;

    private int classicSeatsCount;

    private int premiumSeatsCount;
}
