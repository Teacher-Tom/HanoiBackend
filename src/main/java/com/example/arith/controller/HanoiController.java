package com.example.arith.controller;

import com.example.arith.entity.HanoiResult;
import com.example.arith.hanoi.HanoiTowerInput;
import com.example.arith.service.IHanoiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author 12042
 * @create 2023/1/8 0:02
 */
@RestController
@RequestMapping("/hanoi")
public class HanoiController {

    @Autowired
    IHanoiService hanoiService;

    @PostMapping("/solve")
    public HanoiResult solve(@RequestParam Integer numTower,@RequestParam Integer numDisk){
        HanoiTowerInput input = hanoiService.createQuestionInput(numTower, numDisk);
        HanoiResult result = hanoiService.solve(input);
        return result;
    }
}
