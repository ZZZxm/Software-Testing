package team.zzb.softwaretesting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import team.zzb.softwaretesting.service.TriangleService;
import team.zzb.softwaretesting.serviceImpl.exception.TriangleIllegalException;

import java.util.Collection;

/**
 * @author Y.C.Young
 * @project Software Testing
 * @classname TriangleController
 * @description TODO
 * @date 2021/5/24 14:20
 */
@CrossOrigin
@RestController
@RequestMapping("/triangle")
public class TriangleController {

    @Autowired
    public TriangleService triangleService;

    @PostMapping("/program")
    public String program(double edgeA, double edgeB, double edgeC) {

        String actual;
        try {
            actual = triangleService.judgeType(edgeA, edgeB, edgeC);
        } catch (TriangleIllegalException e) {
            actual = e.getMessage();
        }
        return actual;
    }


    @PostMapping("/boundaryTest")
    public Collection boundaryTest() {
        return triangleService.boundaryTest();
    }

    @PostMapping("/equivClassTest")
    public Collection equivClassTest() {
        return triangleService.equivClassTest();
    }

}
