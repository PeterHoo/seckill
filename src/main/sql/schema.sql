--数据库初始化脚本

--创建数据库
CREATE DATABASE seckill;
--使用数据库
use seckill;
--创建秒杀库存表，只有innoDB引擎支持事物
CREATE TABLE seckill(
`seckill_id` bigint NOT NULL AUTO_INCREMENT COMMENT '商品库存id',
`name` varchar(120) NOT NULL  COMMENT '商品名称',
`number` int NOT NULL COMMENT '库存数量',
`start_time` TIMESTAMP NOT NULL COMMENT '秒杀开启时间',
`end_time` TIMESTAMP NOT NULL COMMENT '秒杀结束时间',
`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (seckill_id),
KEY idx_start_time(start_time),
KEY idx_end_time(end_time),
KEY idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT = 1000 DEFAULT CHARSET=utf8 COMMENT='库存秒杀表';
--初始化数据
insert into
  seckill(name,number,start_time,end_time)
values
  ('1000元秒杀iphone7',100,'2017-2-16 00:00:00','2017-2-17 00:00:00'),
  ('500元秒杀ipad2',200,'2017-2-16 00:00:00','2017-2-17 00:00:00'),
  ('300元秒杀小米4',300,'2017-2-16 00:00:00','2017-2-17 00:00:00'),
  ('200元秒杀红米',400,'2017-2-16 00:00:00','2017-2-17 00:00:00');
--秒杀成功明细表
--用户登录认证相关的信息
CREATE TABLE success_killed(
`seckill_id` bigint NOT NULL COMMENT '秒杀商品id',
`user_phone` bigint  NOT NULL COMMENT '用户手机号',
`status` tinyint NOT NULL DEFAULT -1 COMMENT '状态标示：-1：无效 0：成功 1：已付款 2：已发货',
`create_time` TIMESTAMP NOT NULL  COMMENT '创建时间',
PRIMARY KEY (seckill_id,user_phone),/*联合主键*/
KEY idx_create_time(create_time)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='秒杀成功明细表';
--连接数据库控制台
mysql -uroot -p