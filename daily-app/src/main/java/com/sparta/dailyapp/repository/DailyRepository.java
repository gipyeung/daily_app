package com.sparta.dailyapp.repository;

import com.sparta.dailyapp.dto.DailyRequestDto;
import com.sparta.dailyapp.dto.DailyResponseDto;
import com.sparta.dailyapp.entity.Daily;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public class DailyRepository {

    private final JdbcTemplate jdbcTemplate;

    public DailyRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Daily save(Daily daily) {
        //DB 저장

        KeyHolder keyHolder = new GeneratedKeyHolder(); // 기본 키를 반환받기 위한 객체

        String sql = "INSERT INTO daily_app (activity, name, password, date , date_modify) VALUES (?,  ?, ?, now(),now())";
        jdbcTemplate.update( con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);

                    preparedStatement.setString(1, daily.getActivity());
                    preparedStatement.setString(2, daily.getName());
                    preparedStatement.setString(3, daily.getPassword());
                   // preparedStatement.setTimestamp(4, Timestamp.valueOf(daily.getDate()));
                   // preparedStatement.setTimestamp(4, Timestamp.valueOf(daily.getDate()));

                    return preparedStatement;
                },
                keyHolder);

        // DB Insert 후 받아온 기본키 확인
        Long id = keyHolder.getKey().longValue();
        daily.setId(id);

        return daily;
    }

    public List<DailyResponseDto> findall() {
        String sql = "SELECT * FROM daily_app ORDER BY date_modify DESC";

        return jdbcTemplate.query(sql, new RowMapper<DailyResponseDto>() {
            @Override
            public DailyResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                // SQL 의 결과로 받아온 Memo 데이터들을 dailyResponseDto 타입으로 변환해줄 메서드
                String activity = rs.getString("Activity");
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String password = rs.getString("password");
                LocalDateTime date = rs.getTimestamp("date").toLocalDateTime();
                LocalDateTime date_modify = rs.getTimestamp("date_modify").toLocalDateTime();
                return new DailyResponseDto(activity,id, name, password, date, date_modify);
            }
        });
    }

    public void update(Long id, DailyRequestDto requestDto) {
        String sql = "UPDATE daily_app SET activity = ?, name = ?, date_modify = now() WHERE id = ? ";
        jdbcTemplate.update(sql, requestDto.getActivity(),requestDto.getName(),  requestDto.getPassword(), id);
    }

    public void delete(Long id) {
        String sql = "DELETE FROM daily_app WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public Daily findByid(Long id) {
        // DB 조회
        String sql = "SELECT * FROM daily_app WHERE id = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if(resultSet.next()) {
                Daily daily = new Daily();
                daily.setActivity(resultSet.getString("activity"));
                daily.setId(resultSet.getLong("id"));
                daily.setName(resultSet.getString("name"));
                daily.setPassword(resultSet.getString("password"));
                daily.setDate(resultSet.getTimestamp("date").toLocalDateTime());
                daily.setDate_modify(resultSet.getTimestamp("date_modify").toLocalDateTime());
                return daily;
            } else {
                return null;
            }
        }, id);
    }


}
