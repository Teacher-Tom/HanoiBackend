package com.example.arith.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 12042
 * @create 2023/1/8 10:02
 * 存放汉诺塔结果
 */
@Data
@ApiModel("汉诺塔结果实体类HanoiResult")
public class HanoiResult {
    @ApiModelProperty("柱子数量")
    int numTower;
    @ApiModelProperty("盘子数量")
    int numDisk;
    //最少移动
    @ApiModelProperty("最少移动次数")
    int leastMove;
    //总步数
    @ApiModelProperty("总步骤数")
    int totalStep;
    //记录错误数
    @ApiModelProperty("总错误数")
    int errorCount;
    @ApiModelProperty("本次计算是否成功的消息")
    //传递结果消息
    String msg;
    //存放每一步的状态
    @ApiModelProperty("每一步骤的具体状态,每一个元素为HanoiStep类型")
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


