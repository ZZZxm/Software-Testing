package team.zzb.softwaretesting.serviceImpl;

import org.springframework.stereotype.Service;
import team.zzb.softwaretesting.service.CalendarService;
import team.zzb.softwaretesting.serviceImpl.exception.DateIllegalException;

import java.util.Arrays;
import java.util.Collection;

/**
 * @ClassName CalendarServiceImpl
 * @Description TODO
 * @Author Brian.Z
 * @Date 2021/5/9 10:34
 */
@Service("CalendarService")
public class CalendarServiceImpl implements CalendarService {

    private static final String[] weekdays = {"Sunday", "Monday", "Tuesday", "Wednesday",
            "Thursday", "Friday", "Saturday"};

    @Override
    public String searchByDay(int year, int month, int day) throws DateIllegalException {
        if (year < 1900) {
            throw new DateIllegalException("Year must not be less than 1900.");
        }

        if (month < 1 || month > 12) {
            throw new DateIllegalException("Month must be between 1 and 12.");
        }

        if (!checkDayAndMonth(year, month, day)) {
            throw new DateIllegalException("Day and month not matched.");
        }

        int dayCount = 0;
        int leapYear = isLeap(year) ? 1 : 0;

        for (int i = 1900; i < year; i++) {
            if (isLeap(i)) {
                dayCount += 366;
            } else {
                dayCount += 365;
            }
        }

        for (int j = 1; j < month; j++) {
            if (isBigMonth(j)) {
                dayCount += 31;
            } else if (j == 2) {
                dayCount += (28 + leapYear);
            } else {
                dayCount += 30;
            }
        }

        dayCount += day;
        return weekdays[dayCount % 7];
    }

    private boolean checkDayAndMonth(int year, int month, int day) {
        if (day < 1 || day > 31) {
            return false;
        }
        if (day == 29) {
            return month != 2 || isLeap(year);
        }
        if (day == 30) {
            return month != 2;
        }
        if (day == 31) {
            return isBigMonth(month);
        }
        return true;
    }

    private boolean isBigMonth(int month) {
        return month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12;
    }

    private boolean isLeap(int year) {
        if (year % 4 == 0 && year % 100 != 0) {
            return true;
        }
        return year % 400 == 0;
    }

    @Override
    public Collection boundaryTest() {
        Object [][] testcases = {
                {2020, 12, 23, "Wednesday"},
                {2021, 2, 28, "Sunday"},
                {2000, 2, 29, "Tuesday"},
                {2003, 4, 30, "Wednesday"},
                {2021, 8, 31, "Tuesday"},
                {2100, 8, 21, "Saturday"},
                {1899, 6, 1, "Year must not be less than 1900."},
                {1999, 0, 2, "Month must be between 1 and 12."},
                {2021, 2, 31, "Day and month not matched."},
        };

        Object[][] results = new Object[testcases.length][5];
        for (int i = 0; i < testcases.length; i++) {
            int year = (int) testcases[i][0];
            int month = (int) testcases[i][1];
            int day = (int) testcases[i][2];
            String expected = (String) testcases[i][3];

            results[i][0] = year;
            results[i][1] = month;
            results[i][2] = day;
            results[i][3] = expected;
            try {
                results[i][4] = searchByDay(year, month, day);
            } catch (DateIllegalException e) {
                results[i][4] = e.getMessage();
            }
        }
        return Arrays.asList(results);
    }

    @Override
    public Collection equivClassTest() {
        return null;
    }
}

