package org.example;
import java.lang.reflect.Array;
import java.util.*;


public class DataDemo {
    public int removeDuplicates(int[] nums) {
        HashMap<Integer,Integer>map=new HashMap<>();
        int k=nums.length;
        int a=0;
        for(int b=0;b<k;b++){
            if(map.putIfAbsent(nums[b],1)!=null){
                map.put(nums[b],map.get(nums[b])+1);
            }
            nums[a++]=nums[b];
        }
        return a;
    }
}
