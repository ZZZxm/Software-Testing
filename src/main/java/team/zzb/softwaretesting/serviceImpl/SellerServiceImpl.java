package team.zzb.softwaretesting.serviceImpl;

import org.springframework.stereotype.Service;
import team.zzb.softwaretesting.service.SellerService;
import team.zzb.softwaretesting.serviceImpl.exception.SellerIllegalException;

import java.util.ArrayList;
import java.util.Collection;

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
    public double calculateCommission(int hostNum, int monitorNum, int peripheralNum) throws SellerIllegalException {
        final int hostPrice = 25;
        final int monitorPrice = 30;
        final int peripheralPrice = 45;
        if (hostNum <= 0 || monitorNum <= 0 || peripheralNum <= 0 || hostNum > 70 || monitorNum > 80 || peripheralNum > 90) {
            throw new SellerIllegalException("The number of items is illegal.");
            // System.out.println("[Wrong]the number of items is illegal.");
            // return 0;
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


    @Override
    public Collection boundaryTest() {
        return new ArrayList();
    }


    @Override
    public Collection equivClassTest() {
        return new ArrayList();
    }
}
