package com.example.show_time.Converters;

import com.example.show_time.Entity.Ticket;
import com.example.show_time.EntryDtos.TicketEntryDto;

public class TicketConverter {

    public static Ticket convertEntryToEntity(TicketEntryDto ticketEntryDto){

        Ticket ticket = new Ticket();
        return ticket;

    }
}
