package com.example.arith.service;

import com.example.arith.entity.HanoiResult;
import com.example.arith.hanoi.HanoiTowerInput;

/**
 * @Author 12042
 * @create 2023/1/7 23:26
 */
public interface IHanoiService {
    public HanoiTowerInput createQuestionInput(int num_tower, int num_disk);

    public HanoiResult solve(HanoiTowerInput input);
}
