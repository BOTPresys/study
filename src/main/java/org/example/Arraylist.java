package org.example;

import java.util.Arrays;
import java.util.SortedMap;

public class Arraylist {
    private int[] array;
    private int size=0;
    public Arraylist(){
        array=new int[10];
    };
    public Arraylist(int num){
        array=new int[num];
    }
    public void ensureCapicity(int num){
        int length=0;
        if(num>array.length){
            if(num>array.length + (array.length >> 1)){
                length=num;
            }else{
                length=array.length + (array.length >> 1);
            }
            int[] nums =new int[length];
            System.arraycopy(array,0,nums,0,size);
            array=nums;
        }
    }
    public void lastInsert(int value){
        ensureCapicity(size+1);
        array[size]=value;
        size++;
    }
    public void Insert(int location,int value){
        ensureCapicity(size+1);
        for(int i=size;i>location;i--){
            array[i]=array[i-1];
        }
        array[location]=value;
        size++;
    }
    public int remove(int location){
        int num=array[location];
        for(int i=location;i<size-1;i++){
            array[i]=array[i+1];
        }
        size--;
        return num;
    }
    public void show(){
        System.out.println(Arrays.toString(array));
    }
}
