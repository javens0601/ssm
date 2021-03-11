package com.javen.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 两数字之和，利用map来做
 */
public class TwoNumSum {

    public static void main(String[] args) {
        int[] nums = new int[]{2,7,11,15};
        int target = 13;
        int[] res = twoSum(nums, target);
        for (int re : res) {
            System.out.println(re);
        }
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {

                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{0};
    }
}
