package com.example.arith.service.impl;

import com.example.arith.entity.HanoiResult;
import com.example.arith.hanoi.HanoiTowerInput;
import com.example.arith.service.IHanoiService;
import org.springframework.stereotype.Service;

/**
 * @Author 12042
 * @create 2023/1/7 23:47
 */
@Service
public class HanoiServiceImpl implements IHanoiService {
    @Override
    public HanoiTowerInput createQuestionInput(int num_tower, int num_disk) {
        /* Class used to obtain user input for number of Tower and Disk */
        HanoiTowerInput input = new HanoiTowerInput(6,10,50,90);
        /* Request input for number of Tower and Disk */
        if(input.RequestInputWeb(num_tower,num_disk)){
            return input;
        }else {
            return null;
        }
    }

    @Override
    public HanoiResult solve(HanoiTowerInput input) {
        if(input == null){
            HanoiResult result = new HanoiResult(-1,-1);
            result.setMsg("输入不合法");
            return result;
        }
        /* Start to find solution */
        HanoiResult hanoiResult = input.StartSolution();

        return hanoiResult;
    }
}
