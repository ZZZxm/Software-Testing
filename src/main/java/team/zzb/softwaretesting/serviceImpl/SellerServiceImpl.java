package team.zzb.softwaretesting.serviceImpl;

import org.springframework.stereotype.Service;
import team.zzb.softwaretesting.service.SellerService;

/**
 * @author Kerr
 * @project
 * @classname SellerServiceImpl
 * @description TODO
 * @date 2021/5/10 10:18
 */
@Service("SellerService")
public class SellerServiceImpl implements SellerService {
    @Override
    public double problem3(int hostNum, int monitorNum, int peripheralNum) {
        final int hostPrice = 25;
        final int monitorPrice = 30;
        final int peripheralPrice = 45;
        if (hostNum <= 0 || monitorNum <= 0 || peripheralNum <= 0 || hostNum > 70 || monitorNum > 80 || peripheralNum > 90) {
            System.out.println("[Wrong]the number of item is illegal.");
            return 0;
        }
        int total = hostPrice * hostNum + monitorPrice * monitorNum + peripheralPrice * peripheralNum;
        if (total <= 1000) {
            return total * 0.1;
        } else if (total <= 1800) {
            return total * 0.15;
        } else {
            return total * 0.2;
        }
    }
}
