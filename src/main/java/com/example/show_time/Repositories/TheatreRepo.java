package com.example.show_time.Repositories;

import com.example.show_time.Entity.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheatreRepo extends JpaRepository<Theatre,Integer> {

}
