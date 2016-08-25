# Redis cluster搭建

## 1. Redis安装启动
   - 安装

   到redis官网下载redis3.0安装包，以linux为例：
   ````shell
   $ wget http://download.redis.io/releases/redis-3.2.3.tar.gz
   $ tar xzf redis-3.2.3.tar.gz
   $ cd redis-3.2.3
   $ make
   $ make install
   ````
   - 配置

   以3台机器搭建6个节点组成3主3从集群，每台机器上启动两个实例，以10.148.195.90机器为例，两个redis实例端口分别为6379、6380：

    1. 先创建redis相关目录（/etc/redis  /usr/local/redis）    
   etc: 存放redis配置文件  
   usr：存放redis数据文件
   ````shell
   mkdir -p /etc/redis
   mkdir -p /usr/local/redis/6379
   mkdir -p /usr/local/redis/6380
   cp redis.conf /etc/redis/6379.conf
   cp redis.conf /etc/redis/6380.conf
   ````
    2. 修改配置文件，如修改6379.conf

      ````properties
      port 6379
      #绑定本机ip
      bind 10.148.195.90 127.0.0.1
      #以守护进程运行
      daemonize yes
      #数据文件存放目录
      dir /usr/local/redis/6379

      ````
     集群相关配置

      ````
      #开启集群 (637行左右)
     cluster-enabled yes
     #节点配置文件(645行)
     cluster-config-file 6379.conf
     #节点连接超时时间(毫秒为单位，约651行)
     cluster-node-timeout 5000
     #开启aof持久化
     appendonly yes
     ````
     参考上面配置修改6380相关配置，其他机器重复上述配置。

    3. 修改启动脚本
       拷贝源码包中utils文件夹内的redis_init_script，修改端口配置

       ````shell
        #!/bin/sh
        #
        # Simple Redis init.d script conceived to work on Linux systems
        # as it does use of the /proc filesystem.

        REDISPORT=6379
        EXEC=/usr/local/bin/redis-server
        CLIEXEC=/usr/local/bin/redis-cli

        PIDFILE=/var/run/redis_${REDISPORT}.pid
        CONF="/etc/redis/${REDISPORT}.conf"

        case "$1" in
            start)
                if [ -f $PIDFILE ]
                then
                        echo "$PIDFILE exists, process is already running or crashed"
                else
                        echo "Starting Redis server..."
                        $EXEC $CONF
                fi
                ;;
            stop)
                if [ ! -f $PIDFILE ]
                then
                        echo "$PIDFILE does not exist, process is not running"
                else
                        PID=$(cat $PIDFILE)
                        echo "Stopping ..."
                        $CLIEXEC -p $REDISPORT shutdown
                        while [ -x /proc/${PID} ]
                        do
                            echo "Waiting for Redis to shutdown ..."
                            sleep 1
                        done
                        echo "Redis stopped"
                fi
                ;;
            *)
                echo "Please use start or stop as first argument"
                ;;
        esac
   ````
   调用脚本将各个redis实例启动


 ## 2. 创建集群
   使用redis源码中提供的脚步工具创建集群，脚步工具需要安装ruby环境
   - 安装ruby

    以debian系统为例
   ````
   apt-get install ruby
   apt-get install rubygem
   gem install redis
   ````
   - 创建集群
   我们使用redis-trib.rb来创建redis cluster集群，进入源码包src目录
   ````shell
   ./redis-trib.rb create --replicas 1 10.148.195.90:6379 10.148.195.90:6380 10.148.195.91:6379 10.148.195.91:6380 10.148.195.92:6379 10.148.195.92:6380
   ````
   当出现如下表示创建成功。








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
