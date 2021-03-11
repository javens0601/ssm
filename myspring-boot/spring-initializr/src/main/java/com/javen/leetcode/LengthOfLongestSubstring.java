package com.javen.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 */
public class LengthOfLongestSubstring {
    public static void main(String[] args) {
        //String str = "aawkfrsbwswhk";
        String str = "aab";
        System.out.println(lengthOfLongestSubstring(str));
    }

    //利用滑动窗口来实现
    public static int lengthOfLongestSubstring(String s) {
        int left = 0;
        int right = -1;
        char[] chars = s.toCharArray();
        Set<Character> set = new HashSet<>();
        int tmp = 0;
        for (int i = 0; i < chars.length; i++) {
            if (left == 0 || left <= right) {
                if (set.add(chars[i])) {
                    right++;
                } else {
                    left++;
                }
                tmp = Math.max(tmp, right - left + 1);


                System.out.println(tmp + " " + left + " " + right);
            }
        }
        if (chars.length == 0) {
            return 0;
        }
        return tmp + 1;

    }
}
