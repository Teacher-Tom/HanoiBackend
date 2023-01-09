package com.example.arith.hanoi;

import com.example.arith.entity.HanoiResult;


/**
 * @Author 12042
 * @create 2023/1/8
 * 获得用户输入并求解
 */
public class HanoiInput {
    /**
     * 柱子数量
     */
    private int numTower;
    /**
     * 盘子数量
     */
    private int numDisk;
    /**
     * 柱子数量下界
     */
    private int towerLB;
    /**
     * 柱子数量上界
     */
    private int towerHB;
    /**
     * 盘子数量下界
     */
    private int diskLB;
    /**
     * 盘子数量上界
     */
    private int diskHB;

    /**
     * 默认的边界条件
     */
    public HanoiInput(){
        towerLB = 6;
        towerHB = 10;
        diskLB = 50;
        diskHB = 90;
    }

    /**
     * 根据用户输入初始化边界条件
     */
    public HanoiInput(int towerLB, int towerHB, int diskLB, int diskHB){
        this.towerLB = towerLB;
        this.towerHB = towerHB;
        this.diskLB = diskLB;
        this.diskHB = diskHB;
    }


    //从前端输入参数初始化算法
    public boolean input(int num_tower, int num_disk){
        System.out.print("柱子个数边界:" + this.towerLB + "-" +this.towerHB);
        numTower = num_tower;
        //检查输入是否合法
        if (!isValidTower(numTower)){
            System.err.println("输入柱子个数不合法");
            return false;
        }
        System.out.print("盘子个数边界:"+this.diskLB +"-"+this.diskHB);
        numDisk = num_disk;
        if (!isValidDisk(numDisk)){
            System.err.println("输入盘子个数不合法");
            return false;
        }
        return true;
    }
    /**
     *
     * 求解问题并返回结果对象
     * @return com.example.arith.entity.HanoiResult
     * @author likailing
     * @create 2023/1/9
     **/

    public HanoiResult getResult(){
        HanoiSolver hanoiSolver = new HanoiSolver(numTower, numDisk);
        return hanoiSolver.getResult();
    }

    /**
     *
     * 检查输入合法性
     * @param tower
     * @return boolean
     * @author likailing
     * @create 2023/1/9
     **/

    private boolean isValidTower(int tower){
        if (tower >= this.towerLB && tower <= this.towerHB)
            return true;
        else
            return false;
    }

    /**
     *
     * 检查输入合法性
     * @param disk
     * @return boolean
     * @author likailing
     * @create 2023/1/9
     **/
    private boolean isValidDisk(int disk){
        if (disk >= this.diskLB && disk <= this.diskHB)
            return true;
        else
            return false;
    }
}
