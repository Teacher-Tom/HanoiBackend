package com.example.arith.entity;

import com.example.arith.hanoi.PegsStack;
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
public class HanoiStep {
    //当前步数
    int currentStep;
    //所有柱子上的盘子状态
    List<Stack<Integer>> pegStackList;

    public HanoiStep(int currentStep, PegsStack pegStacks[],int numTower) {
        pegStackList = new ArrayList<>();
        this.currentStep = currentStep;
        for (int i = 0;i <numTower;i++) {
            Stack<Integer> stackValue = null;
            if(pegStacks[i]!=null){
                stackValue = pegStacks[i].PegStackValue;
            }
            this.pegStackList.add(stackValue);
        }

    }

}
