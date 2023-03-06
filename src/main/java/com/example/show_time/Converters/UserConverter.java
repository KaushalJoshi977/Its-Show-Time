package com.example.show_time.Converters;

import com.example.show_time.Entity.UserEntity;
import com.example.show_time.EntryDtos.UserEntryDto;

public class UserConverter {

    public static UserEntity convert(UserEntryDto userEntryDto){
        UserEntity userEntity = UserEntity.builder().age(userEntryDto.getAge()).address(userEntryDto.getAddress())
                .mobNo(userEntryDto.getMobNo()).name(userEntryDto.getName()).email(userEntryDto.getEmail()).build();

        return userEntity;
    }
}
