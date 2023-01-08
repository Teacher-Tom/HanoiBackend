package com.example.arith.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 12042
 * @create 2023/1/8 10:02
 * 存放汉诺塔结果
 */
@Data
public class HanoiResult {
    int numTower;
    int numDisk;
    //最少移动
    int leastMove;
    //总步数
    int totalStep;
    //记录错误数
    int errorCount;
    //传递结果消息
    String msg;
    //存放每一步的状态
    List<HanoiStep> stepList;

    public HanoiResult(int numTower, int numDisk) {
        this.numTower = numTower;
        this.numDisk = numDisk;
        leastMove = 0;
        totalStep = 0;
        errorCount = 0;
        stepList = new ArrayList<>();
    }

    public void addStep(HanoiStep step){
        stepList.add(step);
    }

}


