package com.example.show_time.Controllers;

import com.example.show_time.EntryDtos.TheatreEntryDto;
import com.example.show_time.Services.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/theatre")
public class TheatreController {

    @Autowired
    TheatreService theaterService;

    @PostMapping("/add")
    public ResponseEntity addTheater(@RequestBody TheatreEntryDto theaterEntryDto){

        try{
            String result = theaterService.addTheatre(theaterEntryDto);
            return new ResponseEntity(result, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }



    }
}
