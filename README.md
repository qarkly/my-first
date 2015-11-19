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
* Doug Lea's workstation
  http://g.oswego.edu/
* leetcode
https://siddontang.gitbooks.io/leetcode-solution/content/linked_list/linked_list_cycle.html
* G1
 http://www.oracle.com/technetwork/tutorials/tutorials-1876574.html
* lamda
https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html
* 工具收集
http://segmentfault.com/q/1010000002404545?hmsr=toutiao.io&utm_medium=toutiao.io&utm_source=toutiao.io

###单词博弈

题目如下： 

甲乙两个人用一个英语单词玩游戏。两个人轮流进行，每个人每次从中删掉任意一个字母，如果剩余的字母序列是严格单调递增的（按字典序a < b < c <....

输入： 一连串英文小写字母，长度不超过15,保证最开始的状态不是一个严格单增的序列。 

输出：1表示甲可以赢，0表示甲不能赢。 

例如: 输入 bad， 则甲可以删掉b或者a,剩余的是ad或者bd，他就赢了，输出1。 

又如: 输入 aaa， 则甲只能删掉1个a，乙删掉一个a,剩余1个a，乙获胜，输出0。 

 

  public static int who(String in); 
 
并发控制
Apache Jackrabbit内部的并发模型相当复杂，在JackRabbit 1.x发布周期内报告并修复了大量的死锁问题。这篇文章是设计和code review的成果，旨在主动预防其他类似的问题

此文是关于Jackrabbit的内部并发和同步机制模型，不是关于JCR的锁特性。注意此文是从架构层面审阅并发控制，并不关注像个别类或组件的多线程安全之类的问题。
此review是基于JackRabbit 1.5版本的默认版本

架构背景


从并发控制方面，Jackrabbit 架构大概可以分成下面的五个层次
1.集群
2.仓库
3.工作空间
4.会话
5.事务

