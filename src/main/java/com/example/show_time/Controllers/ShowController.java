package com.example.show_time.Controllers;

import com.example.show_time.Entity.Show;
import com.example.show_time.EntryDtos.ShowEntryDto;
import com.example.show_time.ResponseDto.ShowResponseDto;
import com.example.show_time.Services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/show")
public class ShowController {

    @Autowired
    ShowService showService;

    @PostMapping("/add")
    public ResponseEntity<String> addShow(@RequestBody ShowEntryDto showEntryDto){

        return new ResponseEntity<>(showService.addShow(showEntryDto), HttpStatus.CREATED);

    }
    @GetMapping("/getShows")
    public ResponseEntity<List<ShowResponseDto>> getShows(@RequestParam int theatreId){
        List<ShowResponseDto> showList = showService.getShows(theatreId);

        if(showList.isEmpty()) return ResponseEntity.noContent().build();
        else return ResponseEntity.ok(showList);
    }


}
