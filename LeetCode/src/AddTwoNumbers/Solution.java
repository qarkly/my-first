package org.qarkly.leetcode.AddTwoNumbers;

/**
 * Created with IntelliJ IDEA.
 * User: qarkly
 * Date: 14-7-31
 * Time: 下午11:45
 * To change this template use File | Settings | File Templates.
 */
public class Solution {

        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            if(l1 == null || l2==null){
                return l1==null?l2:l1;
            }

            ListNode result = null;
            ListNode p = null;
            ListNode p1 = l1,p2 = l2;

            int fag = 0;
            while (p1!= null || p2 != null){
                int sum = (p1==null?0:p1.val)+(p2==null?0:p2.val);
                sum+=fag;
                ListNode node = new ListNode(sum%10);
                fag = sum/10;
                if(result == null){
                    result = node;
                    p= node;
                }else{
                    result.next = node;
                    result = node;
                }
				if(p1 != null){
					p1 = p1.next;
				}
                if(p2 != null){
					p2 = p2.next;
				}
              
            }
            if(fag != 0){
                result.next = new ListNode(fag);
            }

            return p;

        }


    public static class ListNode {
             int val;
             ListNode next;
             ListNode(int x) {
                     val = x;
                     next = null;
                 }
         }


    public static void main(String[] args) {
        ListNode l1 = null, l2= null;
        ListNode p1= null,p2=null;
        l1 = new ListNode(2);
        p1 = l1;
        l1.next = new ListNode(4);
        l1 = l1.next;
        l1.next = new ListNode(3);


        l2 = new ListNode(5);
        p2 = l2;
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        print(p1);
        print(p2);
       ListNode result =  new Solution().addTwoNumbers(p1,p2);
       while (result != null){
           System.out.print(result.val + "-");
           result = result.next;
       }
    }

    public static  void print(ListNode node){
        while (node != null){
            System.out.print(node.val+"-");
            node = node.next;
        }
        System.out.println();
    }
}
