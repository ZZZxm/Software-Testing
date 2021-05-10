package team.zzb.softwaretesting.serviceImpl;

import org.springframework.stereotype.Service;
import team.zzb.softwaretesting.service.TelegramService;
import team.zzb.softwaretesting.serviceImpl.exception.FeeIllegalException;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author Yuan.Cai
 * @classname TelegramServiceImpl
 * @description TODO
 * @date 2021/5/9 22:28
 */
@Service("TelegramService")
public class TelegramServiceImpl implements TelegramService {
    public String thisMonthFee(double callTime, double notPayCount) throws FeeIllegalException
    {
        if(callTime<0){
            throw new FeeIllegalException("Call time is negative.");
        }
        if(callTime>43200){
            throw new FeeIllegalException("Call time exceeds the maximum.");
        }
        if(notPayCount<0){
            throw new FeeIllegalException("Not pay count is negative.");
        }
        if(notPayCount>12){
            throw new FeeIllegalException("Not pay count exceeds the maximum.");
        }
        if (!(notPayCount == Math.floor(notPayCount)) || Double.isInfinite(notPayCount)) {
            throw new FeeIllegalException("Not pay count is not an integer.");
        }
        double totalFee=25.00;
        DecimalFormat df= new DecimalFormat("######0.00");
        if(callTime>0&&callTime<=60&&notPayCount<=1)
        {
            totalFee+=callTime*0.15*0.99;
            String fees=df.format(totalFee);
            return fees;
        }
        if(callTime>60&&callTime<=120&&notPayCount<=2)
        {
            totalFee+=callTime*0.15*0.985;
            String fees=df.format(totalFee);
            return fees;
        }

        if(callTime>120&&callTime<=180&&notPayCount<=3)
        {
            totalFee+=callTime*0.15*0.98;
            String fees=df.format(totalFee);
            return fees;
        }

        if(callTime>180&&callTime<=300&&notPayCount<=3)
        {
            totalFee+=callTime*0.15*0.975;
            String fees=df.format(totalFee);
            return fees;
        }
        if(callTime>300&&notPayCount<=6)
        {
            totalFee+=callTime*0.15*0.97;
            String fees=df.format(totalFee);
            return fees;
        }
        totalFee+=callTime*0.15;
        String fees=df.format(totalFee);
        return fees;
    }

    @Override
    public Collection boundaryTest() {
        Object[][] testcases = {
                {"T1", 0, 0, "25.00"},
                {"T2", 1, 0,  "25.15"},
                {"T3", 30, 0, "29.46"},
                {"T4", 59, 0,  "33.76"},
                {"T5", 60, 0,  "33.91"},
                {"T6", 30, 1, "29.46"},
                {"T7", 30, 2,  "29.50"},
                {"T8", 60, 1,  "33.91"},
                {"T9", 61, 1,  "34.01"},
                {"T10", 90, 1, "38.30"},
                {"T11", 119, 1, "42.58"},
                {"T12", 120, 1, "42.73"},
                {"T13", 90, 0, "38.30"},
                {"T14", 90, 2, "38.30"},
                {"T15", 90, 3, "38.50"},
                {"T16", 120, 2, "42.73"},
                {"T17", 121, 2, "42.79"},
                {"T18", 150, 2, "47.05"},
                {"T19", 179, 2, "51.31"},
                {"T20", 180, 2, "51.46"},
                {"T21", 150, 0, "47.05"},
                {"T22", 150, 1, "47.05"},
                {"T23", 150, 3, "47.05"},
                {"T24", 150, 4, "47.50"},
                {"T25", 180, 2, "51.46"},
                {"T26", 181, 2, "51.47"},
                {"T27", 240, 2, "60.10"},
                {"T28", 299, 2, "68.73"},
                {"T29", 300, 2, "68.88"},
                {"T30", 240, 0, "60.10"},
                {"T31", 240, 1, "60.10"},
                {"T32", 240, 3, "60.10"},
                {"T33", 240, 4, "61.00"},
                {"T34", 300, 3, "68.88"},
                {"T35", 301, 3, "68.80"},
                {"T36", 700, 3, "126.85"},
                {"T37", 1000, 3, "170.50"},
                {"T38", 700, 0, "126.85"},
                {"T39", 700, 1, "126.85"},
                {"T40", 700, 5, "126.85"},
                {"T41", 700, 6, "126.85"},
                {"T42", 700, 7, "130.00"},
                {"T43", -1, 3, "Call time is negative."},
                {"T44", 700, -1, "Not pay count is negative."},
                {"T45", 700, 1.5, "Not pay count is not an integer."}
        };

        Object[][] results = new Object[testcases.length][5];
        for (int i = 0; i < testcases.length; i++) {
            double callTime= (double) testcases[i][1];
            double notPayCount= (double) testcases[i][2];
            String expected = (String) testcases[i][3];

            results[i][0] = testcases[i][0];
            results[i][1] = callTime;
            results[i][2] = notPayCount;
            results[i][3] = expected;
            try {
                results[i][4] = thisMonthFee(callTime,notPayCount);
            } catch (FeeIllegalException e) {
                results[i][4] = e.getMessage();
            }
        }
        return Arrays.asList(results);
    }
    @Override
    public Collection equivClassTest() {
        Object[][] testcases = {
                {"T1", 30, 0, "29.46"},
                {"T2", 90, 1,  "38.30"},
                {"T3", 150, 2, "47.05"},
                {"T4", 240, 2,  "60.10"},
                {"T5", 700, 4,  "126.85"},
                {"T6", 700, 8, "130.00"},
                {"T7", 150, -2,  "Not pay count is negative."},
                {"T8", 150, 13,  "Not pay count exceeds the maximum."},
                {"T9", 150, 1.5,  "Not pay count is not an integer."},
                {"T10", -2, 3, "Call time is negative."},
                {"T11", 45000, 3, "Call time exceeds the maximum."}
        };

        Object[][] results = new Object[testcases.length][5];
        for (int i = 0; i < testcases.length; i++) {
            double callTime= (double) testcases[i][1];
            double notPayCount= (double) testcases[i][2];
            String expected = (String) testcases[i][3];

            results[i][0] = testcases[i][0];
            results[i][1] = callTime;
            results[i][2] = notPayCount;
            results[i][3] = expected;
            try {
                results[i][4] = thisMonthFee(callTime,notPayCount);
            } catch (FeeIllegalException e) {
                results[i][4] = e.getMessage();
            }
        }
        return Arrays.asList(results);
    }
}
