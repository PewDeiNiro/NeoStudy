package com.neostudy.task;

import com.neostudy.task.controller.ApiController;
import com.neostudy.task.dto.CalculateDTO;
import com.neostudy.task.exception.IllegalRequestBodyException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class NeoStudyApplicationTests {

    @InjectMocks
    private ApiController controller;

    @Test
    public void test1() throws ParseException {
        CalculateDTO calculateDTO = new CalculateDTO(60000, 6, null);
        ResponseEntity<String> response = controller.calculate(calculateDTO);
        Assertions.assertEquals("Vacation pay: 12162", response.getBody());
    }

    @Test
    public void test2() throws ParseException {
        CalculateDTO calculateDTO = new CalculateDTO(80000, -1, new String[]{"14.09.2024", "25.09.2024"});
        ResponseEntity<String> response = controller.calculate(calculateDTO);
        Assertions.assertEquals("Vacation pay: 2702", response.getBody());
    }

    @Test
    public void test3(){
        CalculateDTO calculateDTO = new CalculateDTO(7000, -1, null);
        Assertions.assertThrows(IllegalRequestBodyException.class, () -> {controller.calculate(calculateDTO);});
    }

    @Test
    public void test4(){
        CalculateDTO calculateDTO = new CalculateDTO(-1, 15, null);
        Assertions.assertThrows(IllegalRequestBodyException.class, () -> {controller.calculate(calculateDTO);});
    }

}
