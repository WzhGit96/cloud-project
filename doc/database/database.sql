-- Copyright © 2019-2019 Wzh.All rights reserved.
-- 用户表
-- id
-- 帐户
-- 密码
-- 昵称
-- 头像 存放图片路径
-- 邮箱
-- 等级
-- yc（yc度）
-- 鸽子数dove
-- 状态 -1.冻结 1.正常
-- 五个备用字段
CREATE TABLE users (
  id      INT PRIMARY KEY AUTO_INCREMENT,
  account VARCHAR(20),
  pwd     VARCHAR(20),
  name    VARCHAR(20),
  pic     VARCHAR(100),
  email   VARCHAR(30),
  ulevel  INT,
  yc      INT,
  dove    INT,
  status  INT,
  remark1 VARCHAR(1000),
  remark2 VARCHAR(1000),
  remark3 VARCHAR(1000),
  remark4 VARCHAR(1000),
  remark5 VARCHAR(1000)
);

-- 管理员表
-- id
-- 帐户
-- 密码
-- email
-- 状态 1.root 2.普通管理员 0.未激活 -1.冻结
-- 五个备用字段
CREATE TABLE manager (
  id      INT PRIMARY KEY AUTO_INCREMENT,
  account VARCHAR(20),
  pwd     VARCHAR(20),
  email   VARCHAR(30),
  status  INT,
  remark1 VARCHAR(1000),
  remark2 VARCHAR(1000),
  remark3 VARCHAR(1000),
  remark4 VARCHAR(1000),
  remark5 VARCHAR(1000)
);

-- 任务表
-- id
-- uid 创建用户
-- 创建时间
-- 开始时间
-- 持续时间
-- 标题
-- 描述
-- 状态 0.未开始 1.正常 2.已经结束 -1.冻结
-- 五个备用字段
CREATE TABLE task (
  id         INT PRIMARY KEY AUTO_INCREMENT,
  uid        INT,
  createtime BIGINT,
  starttime  BIGINT,
  endtime    BIGINT,
  title      VARCHAR(20),
  describe   VARCHAR(200),
  status     INT,
  remark1    VARCHAR(1000),
  remark2    VARCHAR(1000),
  remark3    VARCHAR(1000),
  remark4    VARCHAR(1000),
  remark5    VARCHAR(1000)
);

-- 参加人员表
-- id
-- tid 任务表id
-- uid 参与的用户
-- 状态 0.未确认未参加 1.确认参加
-- 五个备用字段
CREATE TABLE joinuser (
  id      INT PRIMARY KEY AUTO_INCREMENT,
  tid     INT,
  uid     INT,
  status  INT,
  remark1 VARCHAR(1000),
  remark2 VARCHAR(1000),
  remark3 VARCHAR(1000),
  remark4 VARCHAR(1000),
  remark5 VARCHAR(1000)
);

-- 投票表
-- id
-- tid 任务列表
-- uid 用户id
-- 票数
-- 状态 0.未开始 1.开始中 -1.结束
-- 五个备用字段
CREATE TABLE vote (
  id      INT PRIMARY KEY AUTO_INCREMENT,
  tid     INT,
  uid     INT,
  votes   INT,
  status  INT,
  remark1 VARCHAR(1000),
  remark2 VARCHAR(1000),
  remark3 VARCHAR(1000),
  remark4 VARCHAR(1000),
  remark5 VARCHAR(1000)
);

-- 鸽子管理
-- id
-- uid
-- 获得鸽子时间
-- 状态 0.结束 1.正常
-- 五个备用字段
CREATE TABLE dovemanager (
  id        INT PRIMARY KEY AUTO_INCREMENT,
  uid       INT,
  starttime BIGINT,
  status    INT,
  remark1   VARCHAR(1000),
  remark2   VARCHAR(1000),
  remark3   VARCHAR(1000),
  remark4   VARCHAR(1000),
  remark5   VARCHAR(1000)
);