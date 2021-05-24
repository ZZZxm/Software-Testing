package team.zzb.softwaretesting.service;

import team.zzb.softwaretesting.serviceImpl.exception.SellerIllegalException;

import java.util.Collection;

public interface SellerService {

    double calculateCommission(int hostNum, int monitorNum, int peripheralNum)  throws SellerIllegalException;

    Collection boundaryTest();

    Collection equivClassTest();
}
