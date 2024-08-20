package com.sparta.dailyapp.dto;
import lombok.Getter;

import java.sql.*;
import java.time.LocalDateTime;


@Getter
public class DailyRequestDto {

    private Long id;
    private String activity;
    private String name;
    private String password;
    private LocalDateTime date;
    private LocalDateTime date_modify;

}
