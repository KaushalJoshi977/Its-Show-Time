package com.example.show_time.Controllers;

import com.example.show_time.EntryDtos.UserEntryDto;
import com.example.show_time.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody UserEntryDto userEntryDto){

        try{
            String response = userService.adduser(userEntryDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        }catch (Exception e){

            String result = "User could not be added";
            return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
        }

    }
    @PutMapping("/updateNo.")
    public String updateUser(@RequestParam int userId,@RequestParam String newNo){
        return userService.update(userId,newNo);
    }
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable int userId) {
      String response = userService.deleteUserById(userId);
        return ResponseEntity.status(HttpStatus.GONE).body(response);
    }
}
