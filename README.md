## iLearning Project ##

* java NIO：http://tutorials.jenkov.com/java-nio/selectors.html

* 设计网站：
https://dribbble.com/

* paxos算法：
http://blog.csdn.net/heiyeshuwu/article/details/42426811

* google Guava
http://ifeve.com/google-guava/

* git
http://git-scm.com/book/zh/v1

* git学习
http://blog.jobbole.com/25775/

* java 资料
  -http://www.importnew.com/14429.html
*Doug Lea's workstation
  http://g.oswego.edu/

###单词博弈

题目如下： 

甲乙两个人用一个英语单词玩游戏。两个人轮流进行，每个人每次从中删掉任意一个字母，如果剩余的字母序列是严格单调递增的（按字典序a < b < c <....

输入： 一连串英文小写字母，长度不超过15,保证最开始的状态不是一个严格单增的序列。 

输出：1表示甲可以赢，0表示甲不能赢。 

例如: 输入 bad， 则甲可以删掉b或者a,剩余的是ad或者bd，他就赢了，输出1。 

又如: 输入 aaa， 则甲只能删掉1个a，乙删掉一个a,剩余1个a，乙获胜，输出0。 

 

  public static int who(String in); 
 

