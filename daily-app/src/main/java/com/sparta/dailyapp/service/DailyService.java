package com.sparta.dailyapp.service;

import com.sparta.dailyapp.dto.DailyRequestDto;
import com.sparta.dailyapp.dto.DailyResponseDto;
import com.sparta.dailyapp.entity.Daily;
import com.sparta.dailyapp.repository.DailyRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.List;

@Service
public class DailyService {


    private final DailyRepository dailyRepository;

    public DailyService(DailyRepository dailyRepository) {
        this.dailyRepository = dailyRepository;
    }


    public DailyResponseDto createDaily(DailyRequestDto requestDto) {
        Daily daily = new Daily(requestDto);
        // DB 저장
        Daily saveDaily = dailyRepository.save(daily);
        // Entity -> ResponseDto
        DailyResponseDto dailyResponseDto = new DailyResponseDto(daily);
        return dailyResponseDto;
    }

    public List<DailyResponseDto> getDaily() {

        return dailyRepository.findall();
        // DB 조회

    }

    public Long updatedaily(Long id, DailyRequestDto requestDto) {

        Daily daily = dailyRepository.findByid(id);
        if(daily != null) {
            dailyRepository.update(id,requestDto);
            return id;
        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }

        // 해당 메모가 DB에 존재하는지 확인

    }

    public Long deletedaily(Long id) {


        // 해당 메모가 DB에 존재하는지 확인
        Daily daily = dailyRepository.findByid(id);
        if(daily != null) {
            // memo 삭제
            dailyRepository.delete(id);
            return id;
        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }


}
