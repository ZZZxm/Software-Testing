package team.zzb.softwaretesting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.zzb.softwaretesting.service.SellerService;
import team.zzb.softwaretesting.serviceImpl.exception.SellerIllegalException;

import java.util.Collection;

/**
 * @author Kerr
 * @project
 * @classname SellerController
 * @description TODO
 * @date 2021/5/10 10:17
 */
@CrossOrigin
@RestController
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    public SellerService sellerService;

    @PostMapping("/program")
    public String program(int hostNum, int monitorNum, int peripheralNum) {
        String result;
        try {
            double commission = sellerService.calculateCommission(hostNum, monitorNum, peripheralNum);
            result = Double.toString(commission);
        } catch (SellerIllegalException e) {
            result = e.getMessage();
        }
        return result;
    }

    @PostMapping("/boundaryTest")
    public Collection boundaryTest() {
        return sellerService.boundaryTest();
    }

    @PostMapping("/equivClassTest")
    public Collection equivClassTest() {
        return sellerService.equivClassTest();
    }

}
