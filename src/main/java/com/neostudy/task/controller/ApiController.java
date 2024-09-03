package com.neostudy.task.controller;

import com.neostudy.task.dto.CalculateDTO;
import com.neostudy.task.exception.IllegalRequestBodyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;

@RestController
@RequestMapping("/")
public class ApiController {

    @GetMapping("/calculate")
    public ResponseEntity<String> calculate(@RequestBody CalculateDTO calculate) throws ParseException {
        int averageSalary = calculate.getAverageSalary(), weekends = calculate.getWeekends();
        String[] dates = calculate.getDates();
        if (averageSalary < 0 || (weekends < 0 && calculate.getDates() == null)) throw new IllegalRequestBodyException("Illegal request body");
        if (dates != null){
            weekends = 0;
            for (String date : dates) {
                Date temp = new SimpleDateFormat("d.MM.yyyy").parse(date);
                LocalDate localDate = LocalDate.of(temp.getYear(), temp.getMonth() + 1, temp.getDate());
                if (localDate.getDayOfWeek() != DayOfWeek.THURSDAY && localDate.getDayOfWeek() != DayOfWeek.FRIDAY) { // bag: THURSDAY = SATURDAY FRIDAY = SUNDAY
                    weekends++;
                }
            }
        }
        int vacation = weekends != 0 ? (int) (averageSalary / 29.6 * weekends) : 0;
        return new ResponseEntity<>(String.format("Vacation pay: %d", vacation), HttpStatus.OK);
    }

}
