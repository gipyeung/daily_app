package com.sparta.dailyapp.dto;



import com.sparta.dailyapp.entity.Daily;
import lombok.Getter;

import java.sql.*;
import java.time.LocalDateTime;

@Getter
public class DailyResponseDto {
    private Long id;
    private String activity;
    private String name;
    private String password;
    private LocalDateTime date;
    private LocalDateTime date_modify;

    public DailyResponseDto(Daily daily) {
        this.activity = daily.getActivity();
        this.id = daily.getId();
        this.name = daily.getName();
        this.password = daily.getPassword();
        this.date = daily.getDate();
        this.date_modify = daily.getDate_modify();

    }

    public DailyResponseDto(String activity, Long id, String name, String password, LocalDateTime date, LocalDateTime date_modify) {
        this.activity = activity;
        this.id = id;
        this.name = name;
        this.password = password;
        this.date = date;
        this.date_modify = date_modify;
    }
}