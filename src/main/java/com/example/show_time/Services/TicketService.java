package com.example.show_time.Services;

import com.example.show_time.Converters.TicketConverter;
import com.example.show_time.Entity.Show;
import com.example.show_time.Entity.ShowSeat;
import com.example.show_time.Entity.Ticket;
import com.example.show_time.Entity.UserEntity;
import com.example.show_time.EntryDtos.TicketEntryDto;
import com.example.show_time.Repositories.ShowRepo;
import com.example.show_time.Repositories.TicketRepo;
import com.example.show_time.Repositories.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.List;

@Service
public class TicketService {


    @Autowired
    TicketRepo ticketRepository;

    @Autowired
    ShowRepo showRepository;

    @Autowired
    UserRepo userRepository;

    @Autowired
    JavaMailSender javaMailSender;

    public String addTicket(TicketEntryDto ticketEntryDto) throws Exception{


        //1. Create ticket from entryDto : Convert DTO ---> Entity
        Ticket ticket = TicketConverter.convertEntryToEntity(ticketEntryDto);


        //Validation : Check if the requested seats are available or not ?
        boolean isValidRequest = checkValidityofRequestedSeats(ticketEntryDto);

        if(isValidRequest==false){
            throw new Exception("Requested seats are not available");
        }

        //We assume that the requestedSeats are valid


        //Calculate the total amount :
        Show showEntity = showRepository.findById(ticketEntryDto.getShowId()).get();
        List<ShowSeat> showSeatList = showEntity.getListOfShowSeats();
        List<String> requestedSeats = ticketEntryDto.getRequestedSeats();

        int totalAmount = 0;
        for(ShowSeat showSeatEntity:showSeatList){

            if(requestedSeats.contains(showSeatEntity.getSeatNo())){
                totalAmount = totalAmount + showSeatEntity.getPrice();
                showSeatEntity.setBooked(true);
                showSeatEntity.setBookedAt(new Date());
            }
        }

        ticket.setTotalAmount(totalAmount);


        //Setting the other attributes for the ticket
        ticket.setMovieName(showEntity.getMovie().getMovieName());
        ticket.setShowDate(showEntity.getShowDate());
        ticket.setShowTime(showEntity.getShowTime());
        ticket.setTheaterName(showEntity.getTheatre().getName());


        //We need to set that string that talked about requested Seats
        String allotedSeats = getAllotedSeatsfromShowSeats(requestedSeats);
        ticket.setBookedSeats(allotedSeats);


        //Setting the foreign key attributes
        UserEntity userEntity = userRepository.findById(ticketEntryDto.getUserId()).get();

        ticket.setUserEntity(userEntity);
        ticket.setShow(showEntity);

        //Save the parent
        ticket = ticketRepository.save(ticket);


        List<Ticket> ticketList = showEntity.getListOfBookedTickets();
        ticketList.add(ticket);
        showEntity.setListOfBookedTickets(ticketList);

        showRepository.save(showEntity);


        List<Ticket> ticketList1 = userEntity.getBookedTickets();
        ticketList1.add(ticket);
        userEntity.setBookedTickets(ticketList1);

        userRepository.save(userEntity);


        String body = "Hi this is to confirm your booking for seat No "+allotedSeats +"for the movie : " + ticket.getMovieName();


        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setFrom("backeendacciojob@gmail.com");
        mimeMessageHelper.setTo(userEntity.getEmail());
        mimeMessageHelper.setText(body);
        mimeMessageHelper.setSubject("Confirming your booked Ticket");

        javaMailSender.send(mimeMessage);


        return "Ticket has successfully been added";

    }

    private String getAllotedSeatsfromShowSeats(List<String> requestedSeats){

        String result = "";

        for(String seat :requestedSeats){

            result = result + seat +", ";

        }
        return result;

    }


    private boolean checkValidityofRequestedSeats(TicketEntryDto ticketEntryDto){

        int showId = ticketEntryDto.getShowId();

        List<String> requestedSeats = ticketEntryDto.getRequestedSeats();

        Show showEntity = showRepository.findById(showId).get();

        List<ShowSeat> listOfSeats = showEntity.getListOfShowSeats();

        //Iterating over the list Of Seats for that particular show
        for(ShowSeat showSeatEntity : listOfSeats){

            String seatNo = showSeatEntity.getSeatNo();

            if(requestedSeats.contains(seatNo)){

                if(showSeatEntity.isBooked()==true){
                    return false; //Since this seat cant be occupied : returning false
                }
            }
        }
        //All the seats requested were available
        return true;

    }
}