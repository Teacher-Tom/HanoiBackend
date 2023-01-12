package com.example.arith.hanoi;


/**
 * @Author 12042
 * @create 2023/1/8
 * 用动态规划求最少移动和每次移动的盘子数
 */
public class HanoiDP {
    /**
     * 柱子数
     */
    final int NUM_TOWER;
    /**
     * 盘子数
     */
    final int NUM_DISK;
    /**
     * 二维数组记录最少移动次数
     * 行号对应柱子数量, 第一行为3个柱子，列号代表盘子数量，第一列为1个盘子
     */
    public int[][] dpMove;
    /**
     * 二维数组记录最佳条件下第一步需要移动多少个盘子
     * 行号对应柱子数量, 第一行为3个柱子，列号代表盘子数量，第一列为1个盘子
     */
    public int[][] dpBest;

    /**
     * 求解过程
     * @param numTower 柱子数
     * @param numDisk 盘子数
     */
    public HanoiDP(int numTower, int numDisk){
        NUM_TOWER = numTower;
        NUM_DISK = numDisk;

        /* 数组存储了每种条件下的最少移动次数 */
        dpMove = new int[NUM_TOWER -2][NUM_DISK];
        /* 第一行对应Pegs=3的情况, 最少移动应为 2^(Disk Number) -1 */
        for(int i=0;i<numDisk;i++)
            dpMove[0][i] = (int)Math.pow(2.,(double)i+1)-1;
        /* 当柱子数大于3时，对第一和第二列即1个和2个盘子的情况, 最少移动总是 1 和 3 */
            for(int i = 1; i< NUM_TOWER -2; i++) {
                dpMove[i][0] = 1;
                dpMove[i][1] = 3;
            }
        /* 数组记录了最佳情况的每次移动的盘子数量 */
        dpBest = new int[NUM_TOWER -2][NUM_DISK];
        /* 调用方法计算填充两个数组 */
        leastMoves(NUM_DISK, NUM_TOWER);
    }

    /**
     * 动态规划寻找所有情况下的最少移动
     * @param disks 剩余盘子数
     * @param pegs 可用的柱子数
     * @return 最少移动次数
     */
    public int leastMoves(int disks, int pegs){
        /* 越界检查 */
        if (disks<0 || pegs<3)
            return -1;
        /* 检查是否已经计算出当前条件的最少移动次数，是则直接返回 */
        if(dpMove[pegs-3][disks-1]!=0){
            return dpMove[pegs-3][disks-1];
        }

        double least_moves;
        int moving_disk;
        /*第一步和第三步相当于求解m个圆盘r根柱子的问题，第二步相当于求解n-m个圆盘r-1根柱子的问题*/
        least_moves = 2*leastMoves(disks-1,pegs) + leastMoves(1,pegs-1);
        moving_disk = disks-1;
        /* 遍历所有可能的情况以找到最佳情况 */
        for (int r=disks-2;r>0;--r){
            double moves = 2*leastMoves(r,pegs) + leastMoves(disks-r,pegs-1);
            if(moves<0){
                /*数据过大发生了数据溢出,跳过本次*/
                continue;
            }
            /* 更新最佳解 */
            if (moves<least_moves){
                least_moves=moves;
                moving_disk = r;
            }
        }
        /* 更新数组并返回结果 */
        dpBest[pegs-3][disks-1] = moving_disk;
        dpMove[pegs-3][disks-1] = (int)least_moves;
        return (int) least_moves;
    }

    /**
     * 打印最少移动的数组
     */
    public void PrintDPLeastMove(){
        System.out.print("Least Move Table:\nP\\D");
        for(int i = 0; i< NUM_DISK; i++)
            System.out.print("\t\t"+(i+1));
        for(int i = 0; i< NUM_TOWER -2; i++) {
            System.out.print("\n" + (i + 3));
            for(int k = 0; k< NUM_DISK; k++)
                System.out.print("\t\t"+ dpMove[i][k]);
        }
    }

    /**
     * 打印最佳条件
     */
    public void PrintDPBestCase(){
        System.out.print("\n\nMoving Number Table:\nP\\D");
        for(int i = 0; i< NUM_DISK; i++)
            System.out.print("\t\t"+(i+1));
        for(int i = 0; i< NUM_TOWER -2; i++) {
            System.out.print("\n" + (i + 3));
            for(int k = 0; k< NUM_DISK; k++)
                System.out.print("\t\t"+ dpBest[i][k]);
        }
    }

    /**
     *
     * @return 返回最终最少移动次数
     */
    public int totalLeastMove(){
        return dpMove[NUM_TOWER -3][NUM_DISK -1];
    }
}
