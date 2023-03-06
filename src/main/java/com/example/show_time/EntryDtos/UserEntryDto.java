package com.example.show_time.EntryDtos;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserEntryDto {

    private String name;

    private int age;


    private String email;

    private String mobNo;

    private String address;
}
