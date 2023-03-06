package com.example.show_time.Services;

import com.example.show_time.Entity.UserEntity;
import com.example.show_time.EntryDtos.UserEntryDto;
import com.example.show_time.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public String adduser(UserEntryDto userEntryDto)throws Exception,NullPointerException{

        UserEntity userEntity = UserEntity.builder().age(userEntryDto.getAge())
                .name(userEntryDto.getName())
                .email(userEntryDto.getEmail())
                .mobNo(userEntryDto.getMobNo())
                .address(userEntryDto.getAddress()).build();

        userRepo.save(userEntity);

        return "user added";

    }

    public String update(int userId,String newNo){
        UserEntity userEntity = userRepo.findById(userId).get();
        userEntity.setMobNo(newNo);
        userRepo.save(userEntity);
        return  "Mobile number Updated";
    }
    public String deleteUserById(int userId){
        userRepo.deleteById(userId);
        return "User Deleted";
    }
}
