package com.leetcode;

import javax.lang.model.util.ElementScanner6;
import javax.xml.transform.sax.SAXTransformerFactory;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * @auther machi
 * @date 2020/11/23 - 21:19
 */
public class Solution1_10 {

    /**
     * leetcode1 两数之和=目标值
     *
     * @param nums   数组
     * @param target 目标值
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        go:
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    res[0] = i;
                    res[1] = j;
                    break go;
                }
            }
        }
        return res;
    }

    /**
     * leetcode2 两数相加
     *
     * @param l1 链表1 倒序
     * @param l2 链表2
     * @return 返回链表
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int flag = 0;
        ListNode res;
        if (l1.val + l2.val < 10) {
            res = new ListNode(l1.val + l2.val);
        } else {
            flag = 1;
            res = new ListNode(l1.val + l2.val - 10);
        }
        ListNode head = res;
        l1 = l1.next;
        l2 = l2.next;

        while (l1 != null && l2 != null) {


            if (l1.val + l2.val + flag < 10) {
                res.next = new ListNode(l1.val + l2.val + flag);
                flag = 0;
            } else {
                res.next = new ListNode(l1.val + l2.val + flag - 10);
                flag = 1;
            }
            res = res.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        if (l1 == null) {
            while (l2 != null) {
                if (l2.val + flag < 10) {
                    res.next = new ListNode(l2.val + flag);
                    flag = 0;
                } else {
                    res.next = new ListNode(l2.val + flag - 10);
                    flag = 1;
                }
                res = res.next;
                l2 = l2.next;
            }

        }
        if (l2 == null) {
            while (l1 != null) {
                if (l1.val + flag < 10) {
                    res.next = new ListNode(l1.val + flag);
                    flag = 0;
                } else {
                    res.next = new ListNode(l1.val + flag - 10);
                    flag = 1;
                }
                res = res.next;
                l1 = l1.next;
            }

        }
        if (flag == 1) {
            res.next = new ListNode(1);
        }
        return head;
    }

    /**
     * leetcode3 无重复字符最长子串
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s.length()==0)return 0;
        int res = 1;
        for (int i=0;i<s.length();i++){
            Stack<Character> stack = new Stack<>();
            stack.push(s.charAt(i));
            for (int j=i+1;j<s.length();j++){
                if (stack.contains(s.charAt(j))){
                    res=Math.max(stack.size(),res);
                    break;
                }else {
                    stack.push(s.charAt(j));
                    res=Math.max(stack.size(),res);
                }


            }
        }
        return res;
    }

    /**
     * leetcode4 寻找两个正序数组的中位数
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        int i =0;int j =0;int target =(nums1.length+nums2.length)/2+1;
        while (i<nums1.length&&j<nums2.length){
            if (queue.size()==target){
                break;
            }else {
                if (nums1[i]<=nums2[j]){
                    queue.add(nums1[i]);
                    i++;
                }else {
                    queue.add(nums2[j]);
                    j++;
                }
            }
        }

        if (queue.size()!=target){
            int x =target-queue.size();
            if (i==nums1.length){
               while (x>0){
                   queue.add(nums2[j]);
                   j++;x--;
               }
            }else {
                while (x>0){
                    queue.add(nums1[i]);
                    i++;x--;
                }
            }
        }

        if ((nums1.length+nums2.length)%2==0){
            return (queue.poll()+queue.poll())/2.0;
        }else {
            return queue.poll();
        }

    }


    /**
     * leetcode5 最长回文字串   超时了
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        int[] arr=new int[3];
        for (int i=0;i<s.length();i++){
            for (int j=i;j<s.length();j++){
                if (help5(s,i,j)&&j-i+1>arr[0]){
                    arr[0]=j-i+1;arr[1]=i;arr[2]=j;
                }

            }
        }
        return s.substring(arr[1],arr[2]+1);


    }


























    public static void main(String[] args) {
        System.out.println("abc".substring(0,1));

        Solution1_10 solution1_10 =new Solution1_10();
        String babad = solution1_10.longestPalindrome("babad");
        System.out.println(babad);
    }

    /**
     * leetcode2的类
     */
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 判断是否是回文
     * @param s
     * @param i
     * @param j
     * @return
     */
    public static boolean help5(String s,int i ,int j){
        while (i<j){
            if (s.charAt(i)==s.charAt(j)){
                i++;j--;
            }else {
                return false;
            }
        }
        return true;

    }

}
