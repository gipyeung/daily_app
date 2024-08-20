package com.sparta.dailyapp.entity;

import com.sparta.dailyapp.dto.DailyRequestDto;
import java.sql.*;
import java.time.LocalDateTime;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

//activity, name, password, date, date_modify
@Getter
@Setter
@NoArgsConstructor
public class Daily {
    private Long id;
    private String activity;
    private String name;
    private String password;
    @CreatedDate
    private LocalDateTime date;
    @LastModifiedDate
    private LocalDateTime date_modify;

    public Daily(DailyRequestDto requestDto) {

        this.activity = requestDto.getActivity();
        this.id = requestDto.getId();
        this.name = requestDto.getName();
        this.password = requestDto.getPassword();
        this.date = requestDto.getDate();
        this.date_modify = requestDto.getDate_modify();
    }

    public void update(DailyRequestDto requestDto) {
        this.activity = requestDto.getActivity();
        this.id = requestDto.getId();
        this.name = requestDto.getName();
        this.password = requestDto.getPassword();
        this.date = requestDto.getDate();
        this.date_modify = requestDto.getDate_modify();
    }
}