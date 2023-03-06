package com.example.show_time.Repositories;

import com.example.show_time.Entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepo extends JpaRepository<Ticket,Integer> {
}
