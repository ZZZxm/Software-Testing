package team.zzb.softwaretesting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.zzb.softwaretesting.service.SellerService;

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

    @PostMapping("/problem3")
    public double problem3(int hostNum, int monitorNum, int peripheralNum){
        double salary=0;
        salary=sellerService.problem3(hostNum, monitorNum, peripheralNum);
//        String str=hostNum+"*25+"+monitorNum+"*30+"+peripheralNum+"*45->salary:"+salary;
        return salary;
    }
}
