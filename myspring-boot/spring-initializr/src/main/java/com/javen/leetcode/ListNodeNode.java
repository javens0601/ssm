package com.javen.leetcode;

import org.springframework.asm.SpringAsmInfo;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

/**
 * 两数字之和，链表相加
 */

public class ListNodeNode {

    public static void main(String[] args) {

    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l3 = new ListNode(0);
        ListNode p = l3;
        int temp = 0;
        while (l1 != null || l2 != null || temp != 0) {
            int l1val = (l1 != null ? l1.val : 0);
            int l2val = (l2 != null ? l2.val : 0);
            int sumval = l1val + l2val + temp;
            temp = sumval / 10;

            p.next = new ListNode(sumval%10);
            p = p.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        return l3.next;
    }


}


