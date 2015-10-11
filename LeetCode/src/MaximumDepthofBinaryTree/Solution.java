package org.qarkly.leetcode.MaximumDepthofBinaryTree;

/**
 * Created with IntelliJ IDEA.
 * User: qarkly
 * Date: 14-8-5
 * Time: 下午10:40
 * To change this template use File | Settings | File Templates.
 */
public class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        return Math.max(maxDepth(root.left),maxDepth(root.right))+1;

    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(4);
        root.left.left = new TreeNode(2);
        System.out.println(new Solution().maxDepth(root));
    }


//     * Definition for binary tree
      public static class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
      }

}
