package team.zzb.softwaretesting.serviceImpl;

import org.springframework.stereotype.Service;
import team.zzb.softwaretesting.service.TriangleService;
import team.zzb.softwaretesting.serviceImpl.exception.TriangleIllegalException;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Y.C.Young
 * @project Software Testing
 * @classname TriangleServiceImpl
 * @description TODO
 * @date 2021/5/24 14:27
 */
@Service("TriangleService")
public class TriangleServiceImpl implements TriangleService {

    @Override
    public String judgeType(double edgeA, double edgeB, double edgeC) throws TriangleIllegalException {
        double maxEdge = Math.max((Math.max(edgeA, edgeB)), edgeC);
        double minEdge = Math.min((Math.min(edgeA, edgeB)), edgeC);
        edgeB = edgeA + edgeB + edgeC - minEdge - maxEdge;
        edgeA = minEdge;
        edgeC = maxEdge;
        if (edgeA <= 0) {
            throw new TriangleIllegalException("Some edges are less than 0.");
        }
        if (edgeA + edgeB <= edgeC) {
            throw new TriangleIllegalException("The edges don't form a triangle.");
        }
        if (edgeA == edgeB && edgeA == edgeC) {
            return "equilateral triangle";
        }
        if (edgeA == edgeB || edgeA == edgeC || edgeB == edgeC) {
            return "isosceles triangle";
        }
        return "scalene triangle";
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
