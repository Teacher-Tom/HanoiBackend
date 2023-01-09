package com.example.arith.controller;

import com.example.arith.entity.HanoiResult;
import com.example.arith.hanoi.HanoiInput;
import com.example.arith.service.IHanoiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author 12042
 * @create 2023/1/8 0:02
 */
@RestController
@RequestMapping("/hanoi")
@Api("汉诺塔接口")
public class HanoiController {

    @Autowired
    IHanoiService hanoiService;

    @PostMapping("/solve")
    @ApiOperation("求解汉诺塔问题")
    public HanoiResult solve(@RequestParam @ApiParam(value = "柱子数[6, 10]") Integer numTower, @RequestParam @ApiParam(value = "盘子数[50,90]") Integer numDisk){
        HanoiInput input = hanoiService.createQuestionInput(numTower, numDisk);
        HanoiResult result = hanoiService.solve(input);
        return result;
    }
}
