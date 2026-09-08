package org.example;
import org.w3c.dom.css.Counter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.io.*;

//在 main 中写测试用例，专门测试以下三个“临界点”：
//
//在 size = 0 的空表中，调用 add(0, 100)（验证头部插入）。
//
//在 size = 5 的表中，调用 add(5, 999)（验证尾部插入，即 index == size 的情况）。
//
//在 size = 1 的表中，调用 remove(0)（删掉唯一的元素，验证 size - 1 = 0 的循环边界）。定义一个共享变量：static int counter = 0;
//创建两个线程：每个线程执行 for 循环 10000 次，每次 counter++。
//不加同步：启动两个线程，主线程 join 等待它们结束，最后打印 counter。
//预期：你可能会得到 20000，但大概率是 19987 或 19532 这种奇怪的数字（因为 counter++ 不是原子操作，丢了数据）。
//加 synchronized：把 counter++ 包在 synchronized (Main.class) 同步代码块里，再次运行，观察结果是否稳定为 20000。

public class Main {
    static int counter =0;
    public static void main(String[] args) {
        Arraylist array=new Arraylist();
        array.Insert(0,100);
        array.show();
        array=new Arraylist(5);
        array.Insert(5,9999);
        array.show();
        array=new Arraylist(1);
        array.remove(0);
        array.show();
    }
}