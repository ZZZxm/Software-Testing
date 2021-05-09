package team.zzb.softwaretesting.service;

import team.zzb.softwaretesting.serviceImpl.exception.DateIllegalException;

import java.util.Collection;
import java.util.List;

/**
 * @ClassName CalendarService
 * @Description TODO
 * @Author Brian.Z
 * @Date 2021/5/9 10:29
 */
public interface CalendarService {

    String nextDate(int year, int month, int day) throws DateIllegalException;

    Collection boundaryTest();

    Collection equivClassTest();
}
