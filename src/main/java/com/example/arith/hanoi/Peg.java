package com.example.arith.hanoi;

import java.util.Stack;


/**
 * @Author 12042
 * @create 2023/1/8
 * //存储柱子上的盘子编号和柱子的id
 */
public class Peg {
    /**
     * 栈用来保存盘子编号
     */
    public Stack<Integer> stackValue;
    /**
     * 当前柱子的id
     */
    public int id;
    /**
     * 当前柱子是否有盘子
     */
    public boolean hasDisk;

    /**
     * 初始化空的柱子
     */
    public Peg(int id, boolean hasDisk){
        stackValue = new Stack<Integer>();
        this.id = id;
        this.hasDisk = hasDisk;
    }

        /**
         * 初始化空的柱子
         */
    public Peg(Stack<Integer> value, int id, boolean hasDisk){
        stackValue = value;
        this.id = id;
        this.hasDisk = hasDisk;
    }

    /**
     * 检查当前柱子是否为空
     * @return true or false
     */
    public boolean isEmpty(){
        if(this.stackValue.size()==0){
            return true;}
        else
            return false;
    }
}
