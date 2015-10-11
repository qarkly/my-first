package org.qarkly.leetcode.twosum;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: qarkly
 * Date: 14-7-31
 * Time: 上午1:05
 * To change this template use File | Settings | File Templates.
 */
public class Solution {

    public int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2];
        HashMap<Integer,Integer> map = new HashMap<Integer, Integer>(numbers.length);
        for(int i = 0 ;i < numbers.length; i++){

            Integer index = map.get(target - numbers[i]);

            if(index == null){
                map.put(numbers[i],i);
            }else{
                result[0] = i;
                result[1] = index;
            }
        }
        return result;

    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] a= {2,7,9,11};

        int[] result = solution.twoSum(a,9);
        System.out.println(result[0]+":"+result[1]);
    }
}
