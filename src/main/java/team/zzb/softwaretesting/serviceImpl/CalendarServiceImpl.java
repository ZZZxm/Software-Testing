package team.zzb.softwaretesting.serviceImpl;

import org.springframework.stereotype.Service;
import team.zzb.softwaretesting.service.CalendarService;
import team.zzb.softwaretesting.serviceImpl.exception.DateIllegalException;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

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
    public String nextDate(int year, int month, int day) throws DateIllegalException {
        // 判断输入是否合法
        if (year < 1900) {
            throw new DateIllegalException("Year must not be less than 1900.");
        }

        if (month < 1 || month > 12) {
            throw new DateIllegalException("Month must be between 1 and 12.");
        }

        if (!checkDayAndMonth(year, month, day)) {
            throw new DateIllegalException("Day is illegal.");
        }

        boolean nextMonth = false;
        boolean nextYear = false;

        // day=28时，只有平年2月要换月
        if (day == 28) {
            if (!isLeap(year) && month == 2) {
                nextMonth = true;
            }
        }

        // day=29时，只有闰年2月要换月
        if (day == 29) {
            if (isLeap(year) && month == 2) {
                nextMonth = true;
            }
        }

        // day=30时，只有非2月的小月要换月
        if (day == 30) {
            if (!isBigMonth(month)) {
                nextMonth = true;
            }
        }

        // day=31时，只有大月要换月
        if (day == 31) {
            if (isBigMonth(month)) {
                nextMonth = true;
            }
        }

        if (month == 12 && nextMonth) {
            nextYear = true;
        }

        int newYear = nextYear ? year + 1 : year;
        int newMonth = nextMonth ? month + 1 : month;
        if (newMonth > 12) {
            newMonth = 1;
        }
        int newDay = nextMonth ? 1 : day + 1;
        return newYear + "/" + newMonth + "/" + newDay;
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
        Object[][] testcases = {
                {"W1", 2021, 6, 1, "2021/6/2"},
                {"W2", 2020, 5, 2, "2020/5/3"},
                {"W3", 2018, 6, 30, "2018/7/1"},
                {"W4", 2019, 7, 31, "2019/8/1"},
                {"W5", 1980, 1, 16, "1980/1/17"},
                {"W6", 1999, 2, 17, "1999/2/18"},
                {"W7", 2023, 11, 14, "2023/11/15"},
                {"W8", 2010, 12, 15, "2010/12/16"},
                {"W9", 1900, 5, 20, "1900/5/21"},
                {"W10", 1901, 6, 17, "1901/6/18"},
                {"W11", 2199, 6, 15, "2199/6/16"},
                {"W12", 2012, 8, 19, "2012/8/20"},
                {"W13", 1898, 6, 12, "Year must not be less than 1900."},
                {"W14", 1921, 14, 22, "Month must be between 1 and 12."},
                {"W15", 1935, 3, 34, "Day is illegal."},
                {"W16", 2012, 4, 31, "Day is illegal."},
                {"W17", 2012, 2, 30, "Day is illegal."},
                {"W18", 2013, 2, 29, "Day is illegal."},
        };

        Object[][] results = new Object[testcases.length][6];
        for (int i = 0; i < testcases.length; i++) {
            int year = (int) testcases[i][1];
            int month = (int) testcases[i][2];
            int day = (int) testcases[i][3];
            String expected = (String) testcases[i][4];

            results[i][0] = testcases[i][0];
            results[i][1] = year;
            results[i][2] = month;
            results[i][3] = day;
            results[i][4] = expected;
            try {
                results[i][5] = nextDate(year, month, day);
            } catch (DateIllegalException e) {
                results[i][5] = e.getMessage();
            }
        }
        return Arrays.asList(results);
    }

    @Override
    public Collection equivClassTest() {
        Object[][] testcases = {
                {"W1", 2020, 12, 23, "2020/12/24"},
                {"W2", 2021, 2, 28, "2021/3/1"},
                {"W3", 2000, 2, 29, "2000/3/1"},
                {"W4", 2003, 4, 30, "2003/5/1"},
                {"W5", 2021, 8, 31, "2021/9/1"},
                {"W6", 2100, 8, 21, "2100/8/22"},
                {"W7", 2021, 12, 31, "2022/1/1"},
                {"W8", 1899, 6, 1, "Year must not be less than 1900."},
                {"W9", 1999, 0, 1, "Month must be between 1 and 12."},
                {"W10", 2001, 13, 12, "Month must be between 1 and 12."},
                {"W11", 2021, 1, 0, "Day is illegal."},
                {"W12", 2021, 1, 32, "Day is illegal."},
                {"W13", 2022, 2, 29, "Day is illegal."},
                {"W14", 2000, 2, 30, "Day is illegal."},
                {"W15", 2021, 4, 31, "Day is illegal."}
        };

        Object[][] results = new Object[testcases.length][6];
        for (int i = 0; i < testcases.length; i++) {
            int year = (int) testcases[i][1];
            int month = (int) testcases[i][2];
            int day = (int) testcases[i][3];
            String expected = (String) testcases[i][4];

            results[i][0] = testcases[i][0];
            results[i][1] = year;
            results[i][2] = month;
            results[i][3] = day;
            results[i][4] = expected;
            try {
                results[i][5] = nextDate(year, month, day);
            } catch (DateIllegalException e) {
                results[i][5] = e.getMessage();
            }
        }
        return Arrays.asList(results);
    }
}

