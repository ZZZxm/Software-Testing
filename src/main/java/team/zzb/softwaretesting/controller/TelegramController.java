package team.zzb.softwaretesting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.zzb.softwaretesting.service.TelegramService;
import team.zzb.softwaretesting.serviceImpl.exception.FeeIllegalException;

import java.util.Collection;

/**
 * @author Yuan.Cai
 * @classname TelegramController
 * @description TODO
 * @date 2021/5/9 21:51
 */

@CrossOrigin
@RestController
@RequestMapping("/telegram")
public class TelegramController {
    @Autowired
    public TelegramService telegramService;

    @PostMapping("/getMonthFee")
    public String getMonthFee(double callTime, double notPayCount) {

        String result;
        try {
            result = telegramService.thisMonthFee(callTime,notPayCount);
        } catch (FeeIllegalException e) {
            result = e.getMessage();
        }
        return result;
    }

    @PostMapping("/boundaryTest")
    public Collection boundaryTest() {
        return telegramService.boundaryTest();
    }

    @PostMapping("/equivClassTest")
    public Collection equivClassTest() {
        return telegramService.equivClassTest();
    }
}
