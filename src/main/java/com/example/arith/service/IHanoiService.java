package com.example.arith.service;

import com.example.arith.entity.HanoiResult;
import com.example.arith.hanoi.HanoiInput;

/**
 * @Author 12042
 * @create 2023/1/7 23:26
 */
public interface IHanoiService {
    public HanoiInput createQuestionInput(int num_tower, int num_disk);

    public HanoiResult solve(HanoiInput input);
}
