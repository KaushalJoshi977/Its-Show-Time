package com.example.show_time.Entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "theatre")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Theatre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String location;

//Theatre will work as an parent entity
    @OneToMany(mappedBy = "theatre", cascade =  CascadeType.ALL)
    private List<TheatreSeat> theatreSeats = new ArrayList<>();

    @OneToMany(mappedBy = "theatre", cascade = CascadeType.ALL)
    private List<Show> showList = new ArrayList<>();

}
