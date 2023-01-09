package com.example.arith;

import com.example.arith.entity.HanoiResult;
import com.example.arith.hanoi.HanoiDP;
import com.example.arith.hanoi.HanoiInput;
import com.example.arith.service.IHanoiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ArithApplicationTests {

    @Autowired
    private IHanoiService hanoiService;
    @Test
    void contextLoads() {
    }

    @Test
    void testHanoi(){
        HanoiInput input = hanoiService.createQuestionInput(6, 50);
        HanoiResult result = hanoiService.solve(input);
        System.out.println(result);
    }
    @Test
    void testDp(){
        HanoiDP hanoiDP = new HanoiDP(6,50);
        System.out.println(hanoiDP.totalLeastMove());
    }
}
