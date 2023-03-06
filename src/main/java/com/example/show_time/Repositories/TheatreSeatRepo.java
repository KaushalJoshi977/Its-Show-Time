package com.example.show_time.Repositories;

import com.example.show_time.Entity.TheatreSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheatreSeatRepo extends JpaRepository<TheatreSeat,Integer> {
}
