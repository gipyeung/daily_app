package com.sparta.dailyapp.controller;

import com.sparta.dailyapp.dto.DailyRequestDto;
import com.sparta.dailyapp.dto.DailyResponseDto;
import com.sparta.dailyapp.repository.DailyRepository;
import com.sparta.dailyapp.service.DailyService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DailyController {


    private final DailyService dailyService;
    public DailyController(DailyService dailyService) {
        this.dailyService = dailyService;
    }

    @PostMapping("/daily")
    public DailyResponseDto createdaily(@RequestBody DailyRequestDto requestDto) {
        return dailyService.createDaily(requestDto);
        // RequestDto -> Entity

    }

    @GetMapping("/daily")
    public List<DailyResponseDto> getDaily() {
        return dailyService.getDaily();

    }

    @PutMapping("/daily/{id}")
    public Long updatedaily(@PathVariable("id") Long id, @RequestBody DailyRequestDto requestDto) {
        return dailyService.updatedaily(id, requestDto);
        // 해당 메모가 DB에 존재하는지 확인
    }

    @DeleteMapping("/daily/{id}")
    public Long deletedaily(@PathVariable("id") Long id) {
        return dailyService.deletedaily(id);

    }


}