package com.example.show_time.ResponseDto;

import com.example.show_time.Enums.ShowType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShowResponseDto {
    private int id;

    private LocalDate showDate;

    private LocalTime showTime;

    @Enumerated(value = EnumType.STRING)
    private ShowType showType;

    private String movieName;

    private String theatreName;


}
