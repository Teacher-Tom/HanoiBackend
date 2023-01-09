package com.example.arith.hanoi;

import com.example.arith.entity.HanoiResult;
import com.example.arith.entity.HanoiStep;

import java.util.*;


/*
 *
 * 汉诺塔问题求解器
 * @author likailing
 * @create 2023/1/8
 **/

public class HanoiSolver {
    /**
     * 柱子个数
     */
    final int NUM_TOWER;

    /**
     * 盘子个数
     */
    final int NUM_DISK;

    /**
     * 起始柱子
     */
    final Stack<Integer> SOURCE_PEG;

    /**
     * 所有的柱子上的盘子
     */
    final Peg[] TOTAL_PEG;

    /**
     * 记录当前步数
     */
    private int totalStep;

    /**
     * 记录错误数量
     */
    private int errorCount;
    /**
     * 最优解求解器
     */
    final HanoiDP bestResult;
    /*
     *  保存结果和步骤
     **/
    private HanoiResult result;


    /**
     *
     * 求解柱子数大于3的汉诺塔问题
     * @param numTower
     * @param numDisk
     * @return
     * @author likailing
     * @create 2023/1/9
     **/

    public HanoiSolver(int numTower, int numDisk){
        NUM_TOWER = numTower;
        NUM_DISK = numDisk;

        totalStep = 0;
        errorCount = 0;

        result = new HanoiResult(numTower,numDisk);
        /* 第一个柱子, 刚开始应该包含盘子 [n,n-1,.....3,2,1] ，n 是盘子总数 */
        /*编号小的盘子在上*/
        SOURCE_PEG = new Stack<>();
        for(int i = numDisk;i>0;i--){
            SOURCE_PEG.push(i);
        }

        /* 初始化所有的柱子，除了第一根柱子，其他的柱子一开始都为空*/

        TOTAL_PEG = new Peg[numTower];
        /* 设置第一根柱子的初始状态为非空，id为0 */
        TOTAL_PEG[0] = new Peg(SOURCE_PEG,0,true);
        /* 初始化其他柱子状态，设置为空 */
        for(int i = 1; i< NUM_TOWER; i++)
            TOTAL_PEG[i] = new Peg(i,false);

        /* 求当前条件下的最佳解 */
        bestResult = new HanoiDP(NUM_TOWER, NUM_DISK);

        /* 输出动态规划的最佳解 */
        bestResult.PrintDPLeastMove();
        bestResult.PrintDPBestCase();

        /* 打印初始状态 */
        printCurrentStatus();
        recordCurrentStatus();

        /* 求解具体步骤 */
        getSteps(NUM_DISK, NUM_TOWER,0, getAvailableBuff()[0], NUM_TOWER -1);

        /* 打印结果 */
        System.out.println("最少移动次数: " + bestResult.totalLeastMove());
        System.out.println("总步数: " + totalStep);
        System.out.print("错误数: " + errorCount);

        result.setLeastMove(bestResult.totalLeastMove());
        result.setTotalStep(totalStep);
        result.setErrorCount(errorCount);
    }

    public HanoiResult getResult() {
        return result;
    }

    /**
     * 根据前面得到的最佳条件来移动盘子，获得所有步骤
     * @param disk 需要移动的盘子数
     * @param pegs 包含的柱子数
     * @param sourceId 起始柱子id
     * @param buffId 中转柱子id
     * @param destId 目标柱子id
     */
    public void getSteps(int disk, int pegs, int sourceId, int buffId, int destId){
        /* 初始化 buffId, buffId 代表用作中转的柱子的id */
        int bufferId = buffId;

        /*
           moveDisk表示需要移动多少个盘子
        */

        int moveDisk = bestResult.DPBestCase[pegs - 3][disk - 1];
        /* 如果盘子小于等于2个或者柱子小于等于3个或者移动的盘子数<1 就没必要再寻找下一次需要移动的盘子数,使用经典的汉诺塔解法即可 */
        if (disk <= 2 || pegs <= 3 || moveDisk < 1) {
            /* 按经典汉诺塔问题来求解 */
            classicSolve(disk, TOTAL_PEG[sourceId], TOTAL_PEG[bufferId], TOTAL_PEG[destId]);
        }
        else {
            /*
               从起始柱子移动moveDisk个盘子到中转柱子
             */
            getSteps(moveDisk, pegs, sourceId, destId, bufferId);

            //System.out.println("Next Recursion K = " + (disk - K) + " Src=" + (sourceId+1) + "to Dst=" + (destId+1));
            /*
                完成第一步移动后，需要指定新的中转柱子
                此时原先的中转柱子已经使用
                所以要找到下一步移动用的中转柱子
            */
            int newBufferId=bufferId;
            if(!TOTAL_PEG[bufferId].isEmpty()){
                if(getAvailableBuff(destId).length!=0)
                    newBufferId = getAvailableBuff(destId)[0];
            }
            /* 再将起始柱子上disk - moveDisk个盘子移到目标柱子上 */
            getSteps(disk - moveDisk, pegs - 1, sourceId, newBufferId, destId);

            //System.out.println("Next Recursion K = " + K + " Buf=" + (sourceId+1) + "to Dst=" + (destId+1));
            /* 最后将中转柱子上的moveDisk个柱子移动到目标柱子 */
            getSteps(moveDisk, pegs, bufferId, sourceId, destId);
        }

    }

    /**
     * 三个柱子的经典汉诺塔解法
     * @param num 需要移动的盘子数量
     * @param source 起始柱子
     * @param buffer 中转柱子
     * @param destination 目标柱子
     */
    public void classicSolve(int num, Peg source, Peg buffer, Peg destination){
        /* 从起始柱子顶上移动盘子到目标柱子 */
        if(num == 1){
            /*如果目标柱子非空且起始柱子顶部盘子编号大于终点盘子编号，此时不能移动*/
            if(!destination.isEmpty() && source.stackValue.peek() > destination.stackValue.peek()) {
                System.out.print("\n 错误情况  \n");
                errorCount += 1;
            }
            destination.stackValue.push(source.stackValue.pop());
            /* 步骤+1 */
            totalStep += 1;
            /* 打印当前柱子状态 */
            printCurrentStatus();
            recordCurrentStatus();
        }
        /* 递归调用 */
        else{
            /* 移动num-1个盘子到buffer */
            classicSolve(num-1,source,destination,buffer);
            /* 移动1个盘子到destination */
            classicSolve(1,source,buffer,destination);
            /* 从buffer移动num-1个盘子到destination */
            classicSolve(num-1,buffer,source,destination);
        }
    }

    /**
     * 得到所有空柱子用作中转
     * @return 所有空柱子的id
     */
    public int[] getAvailableBuff(){
        int Number = 0;
        /* 遍历所有柱子 */
        for(int i = 0; i< NUM_TOWER; i++)
        {
            /* 计算空柱子数量 */
            if(TOTAL_PEG[i].isEmpty())
                Number += 1;
        }
        /* 初始化数组存储柱子id */
        int[] availableBuffer = new int[Number];
        /* 记录数组下标 */
        int current = 0;
        for(int i = 0; i< NUM_TOWER; i++){
            if( TOTAL_PEG[i].isEmpty()){
                availableBuffer[current] = TOTAL_PEG[i].id;
                current += 1;
            }
        }
        return availableBuffer;
    }

    /**
     * 得到所有除了目标柱子外的空的柱子
     * @param dest 目标柱子
     * @return 可用柱子id
     */
    public int[] getAvailableBuff(int dest){
        int Number = 0;
        for(int i = 0; i< NUM_TOWER; i++)
        {
            /*如果柱子不是目标柱子且为空则可用*/
            if(i!=dest && TOTAL_PEG[i].isEmpty())
                Number += 1;
        }
        int[] availableBuffer = new int[Number];
        int current = 0;
        for(int i = 0; i< NUM_TOWER; i++){
            if(i!=dest && TOTAL_PEG[i].isEmpty()){
                availableBuffer[current] = TOTAL_PEG[i].id;
                current += 1;
            }
        }
        return availableBuffer;
    }

    /**
     * 打印当前步骤的柱子状态
     */
    public void printCurrentStatus(){
        //当前步骤数
        System.out.print("当前步骤: "+ totalStep + "\n");
        //所有柱子状态
        for(int i = 0; i< NUM_TOWER; i++)
            if(TOTAL_PEG[i]!=null)
                System.out.print("柱子 "+ (i+1) + ": "+ TOTAL_PEG[i].stackValue.toString()+"\n");
            else
                System.out.print("柱子 "+ (i+1) + ": [ ]\n");
        System.out.print("============================\n");


    }
    /*
     *
     * 保存当前步骤到结果中
     * @param null
     * @return
     * @author likailing
     * @create 2023/1/9
     **/

    public void recordCurrentStatus(){
        List<Stack<Integer>> pegStackList = new ArrayList<>();
        for (int i = 0;i <NUM_TOWER;i++) {
            Stack<Integer> stack = null;
            if(TOTAL_PEG[i]!=null){
                stack = (Stack<Integer>) TOTAL_PEG[i].stackValue.clone();
            }
            pegStackList.add(stack);
        }
        HanoiStep hanoiStep = new HanoiStep(totalStep, pegStackList);
        result.addStep(hanoiStep);
    }


}
