package team.zzb.softwaretesting.service;

import team.zzb.softwaretesting.serviceImpl.exception.FeeIllegalException;

import java.util.Collection;

public interface TelegramService {
    String thisMonthFee(double callTime, double notPayCount)throws FeeIllegalException;;

    Collection boundaryTest();

    Collection equivClassTest();
}
