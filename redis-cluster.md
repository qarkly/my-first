# Redis cluster搭建

## 1. Redis安装启动



- 移除节点

 命令：
 ````shell
 ./redis-trib del-node 127.0.0.1:700 `<node-id>`
 ````
 说明：
 第一个参数是指集群里任一节点ip:port
 第二个参数是指想要移除节点的ID

 移除master节点也是如此，不过移除master节点内容必须为空。如果master不为空需要将节点上的数据reshard迁移到其他master，然后再移除。
 还有移除master可选的做法就是通过在master的slaves上手动执行failover，将其slave转成新的master移除旧的master。显然这种做法不能减少集群里master的数量，这种做法也需要reshard。


- 升级Resi集群内节点

  升级slave节点非常容易，只需要将节点关闭升级完成后重新启动即可。如果有客户端从slave节点读取，该slave节点不可用时会重新连到其他slave节点。

  升级master有点复杂，建议的步骤如下：
  1. 使用CLUSTER FAILOVER 触发手动failover机制将master换成其它slave节点
  2. 等待master变成slave
  3. 最后按照slave升级步骤进行
  4. 如果你想将升级后的节点重新变成master，再次触发手动fialover将其重新转变成master节点。  


  命令 | 参数 | 说明
 ---- | ---- | ----
 cluster replicate | node-id | 重新设置slave的master节点
 cluster failover | [force] or [takover] | 手动failover
