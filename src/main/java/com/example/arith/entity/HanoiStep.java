package com.example.arith.entity;

import com.example.arith.hanoi.Peg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author 12042
 * @create 2023/1/8 10:18
 * 存放汉诺塔每个步骤
 */
@Data
@ApiModel("每一步的状态HanoiStep")
public class HanoiStep {
    @ApiModelProperty("当前步数")
    int currentStep;
    @ApiModelProperty("移动起点")
    int source;
    @ApiModelProperty("移动目标")
    int destination;
    @ApiModelProperty("每根柱子上的盘子")
    List<Stack<Integer>> pegStackList;

    public HanoiStep(int currentStep, List<Stack<Integer>> stackList, int source, int destination) {
        pegStackList = stackList;
        this.currentStep = currentStep;
        this.source = source;
        this.destination = destination;
    }

}
