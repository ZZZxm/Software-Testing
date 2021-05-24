package team.zzb.softwaretesting.service;

import team.zzb.softwaretesting.serviceImpl.exception.TriangleIllegalException;

import java.util.Collection;

public interface TriangleService {

    String judgeType(double edgeA, double edgeB, double edgeC) throws TriangleIllegalException;

    Collection boundaryTest();

    Collection equivClassTest();

}
