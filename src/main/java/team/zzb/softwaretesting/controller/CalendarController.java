package team.zzb.softwaretesting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.zzb.softwaretesting.service.CalendarService;
import team.zzb.softwaretesting.serviceImpl.exception.DateIllegalException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @ClassName CalendarController
 * @Description TODO
 * @Author Brian.Z
 * @Date 2021/5/9 10:14
 */
@CrossOrigin
@RestController
@RequestMapping("/calendar")
public class CalendarController {

    @Autowired
    public CalendarService calendarService;

    @PostMapping("/searchByDay")
    public String searchByDay(int year, int month, int day) {

        String actual;
        try {
            actual = calendarService.nextDate(year, month, day);
        } catch (DateIllegalException e) {
            actual = e.getMessage();
        }
        return actual;
    }

    @PostMapping("/boundaryTest")
    public Collection boundaryTest() {
        return calendarService.boundaryTest();
    }

    @PostMapping("/equivClassTest")
    public Collection equivClassTest() {
        return calendarService.equivClassTest();
    }
}
