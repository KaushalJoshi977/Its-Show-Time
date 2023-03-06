package com.example.show_time.Entity;

import com.example.show_time.Enums.ShowType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "shows")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate showDate;

    private LocalTime showTime;

    @Enumerated(value = EnumType.STRING)
    private ShowType showType;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;

    //show is child wrt movie and theatre
    @ManyToOne
    @JoinColumn
    private Theatre theatre;
    @ManyToOne
    @JoinColumn
    private Movie movie;

    //Show is parent wrt to ticket
    @OneToMany(mappedBy = "show",cascade = CascadeType.ALL)
    private List<Ticket> listOfBookedTickets = new ArrayList<>();

    @OneToMany(mappedBy = "show",cascade = CascadeType.ALL)
    private List<ShowSeat> listOfShowSeats = new ArrayList<>();
}
