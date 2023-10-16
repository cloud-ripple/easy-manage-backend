-- 用户
create table user
(
    id     int unsigned primary key auto_increment comment 'ID',
    name   varchar(100) comment '姓名',
    age    tinyint unsigned comment '年龄',
    gender tinyint unsigned comment '性别, 1:男, 2:女',
    phone  varchar(11) comment '手机号'
) char set utf8 comment '用户表';

insert into user(id, name, age, gender, phone)
VALUES (null, '白眉鹰王', 55, '1', '18800000000');
insert into user(id, name, age, gender, phone)
VALUES (null, '金毛狮王', 45, '1', '18800000001');
insert into user(id, name, age, gender, phone)
VALUES (null, '青翼蝠王', 38, '1', '18800000002');
insert into user(id, name, age, gender, phone)
VALUES (null, '紫衫龙王', 42, '2', '18800000003');
insert into user(id, name, age, gender, phone)
VALUES (null, '光明左使', 37, '1', '18800000004');
insert into user(id, name, age, gender, phone)
VALUES (null, '光明右使', 48, '1', '18800000005');


-- 部门管理
CREATE TABLE IF NOT EXISTS tb_dept
(
    id          INT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    `name`      VARCHAR(30) NOT NULL UNIQUE COMMENT '部门名称',
    create_time DATETIME    NOT NULL COMMENT '创建时间',
    update_time DATETIME    NOT NULL COMMENT '修改时间'
) COMMENT '部门表' CHARSET utf8;


-- 员工管理
CREATE TABLE IF NOT EXISTS tb_emp
(
    id          INT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    username    VARCHAR(20)      NOT NULL UNIQUE COMMENT '用户名',
    `password`  VARCHAR(32) DEFAULT '123456' COMMENT '密码',
    `name`      VARCHAR(30)      NOT NULL COMMENT '姓名',
    gender      TINYINT UNSIGNED NOT NULL COMMENT '性别, 说明: 1 男, 2 女',
    image       VARCHAR(300) COMMENT '图像',
    job         TINYINT UNSIGNED COMMENT '职位, 说明: 1 班主任,2 讲师, 3 学工主管, 4 教研主管',
    entrydate   DATE COMMENT '入职时间',
    dept_id     INT UNSIGNED COMMENT '部门ID',
    create_time DATETIME         NOT NULL COMMENT '创建时间',
    update_time DATETIME         NOT NULL COMMENT '修改时间'
) COMMENT '员工表' CHARSET utf8;


INSERT INTO tb_dept
VALUES (NULL, '学工部', NOW(), NOW()),
       (NULL, '教研部', NOW(), NOW()),
       (NULL, '咨询部', NOW(), NOW()),
       (NULL, '艺术部', NOW(), NOW()),
       (NULL, '物资部', NOW(), NOW()),
       (NULL, '休闲部', NOW(), NOW()),
       (NULL, '修仙部', NOW(), NOW()),
       (NULL, '文学部', NOW(), NOW()),
       (NULL, '音乐部', NOW(), NOW()),
       (NULL, '动画部', NOW(), NOW()),
       (NULL, '舞蹈部', NOW(), NOW()),
       (NULL, '财务部', NOW(), NOW());



INSERT INTO tb_emp
(id, username, PASSWORD, NAME, gender, image, job, entrydate, dept_id, create_time, update_time)
VALUES (1, 'jinyong', '123456', '金庸', 1, '1.jpg', 4, '2000-01-01', 2, NOW(), NOW()),
       (2, 'zhangwuji', '123456', '张无忌', 1, '2.jpg', 2, '2015-01-01', 2, NOW(), NOW()),
       (3, 'yangxiao', '123456', '杨逍', 1, '3.jpg', 2, '2008-05-01', 2, NOW(), NOW()),
       (4, 'weiyixiao', '123456', '韦一笑', 1, '4.jpg', 2, '2007-01-01', 2, NOW(), NOW()),
       (5, 'changyuchun', '123456', '常遇春', 1, '5.jpg', 2, '2012-12-05', 2, NOW(), NOW()),
       (6, 'xiaozhao', '123456', '小昭', 2, '6.jpg', 3, '2013-09-05', 1, NOW(), NOW()),
       (7, 'jixiaofu', '123456', '纪晓芙', 2, '7.jpg', 1, '2005-08-01', 1, NOW(), NOW()),
       (8, 'zhouzhiruo', '123456', '周芷若', 2, '8.jpg', 1, '2014-11-09', 1, NOW(), NOW()),
       (9, 'dingminjun', '123456', '丁敏君', 2, '9.jpg', 1, '2011-03-11', 1, NOW(), NOW()),
       (10, 'zhaomin', '123456', '赵敏', 2, '10.jpg', 1, '2013-09-05', 1, NOW(), NOW()),
       (11, 'luzhangke', '123456', '鹿杖客', 1, '11.jpg', 1, '2007-02-01', 1, NOW(), NOW()),
       (12, 'hebiweng', '123456', '鹤笔翁', 1, '12.jpg', 1, '2008-08-18', 1, NOW(), NOW()),
       (13, 'fangdongbai', '123456', '方东白', 1, '13.jpg', 2, '2012-11-01', 2, NOW(), NOW()),
       (14, 'zhangsanfeng', '123456', '张三丰', 1, '14.jpg', 2, '2002-08-01', 2, NOW(), NOW()),
       (15, 'yulianzhou', '123456', '俞莲舟', 1, '15.jpg', 2, '2011-05-01', 2, NOW(), NOW()),
       (16, 'songyuanqiao', '123456', '宋远桥', 1, '16.jpg', 2, '2010-01-01', 2, NOW(), NOW()),
       (17, 'chenyouliang', '123456', '陈友谅', 1, '17.jpg', NULL, '2015-03-21', NULL, NOW(), NOW()),
       (19, 'zhang2', '123456', '张二', 1, '2.jpg', 2, '2012-01-01', 2, '2022-10-27 16:35:33', '2022-10-27 16:36:11'),
       (20, 'zhang3', '123456', '张三', 1, '2.jpg', 2, '2018-01-01', 2, '2022-10-27 16:35:33', '2022-10-27 16:36:13'),
       (21, 'zhang4', '123456', '张四', 1, '2.jpg', 2, '2015-01-01', 2, '2022-10-27 16:35:33', '2022-10-27 16:36:15'),
       (22, 'zhang5', '123456', '张五', 1, '2.jpg', 2, '2016-01-01', 1, '2022-10-27 16:35:33', '2022-10-27 16:36:17'),
       (23, 'zhang6', '123456', '张六', 1, '2.jpg', 2, '2012-01-01', 1, '2022-10-27 16:35:33', '2022-10-27 16:36:19'),
       (24, 'zhang7', '123456', '张七', 1, '2.jpg', 2, '2006-01-01', 2, '2022-10-27 16:35:33', '2022-10-27 16:36:21'),
       (25, 'zhang8', '123456', '张八', 1, '2.jpg', 2, '2002-01-01', 1, '2022-10-27 16:35:33', '2022-10-27 16:36:23'),
       (26, 'zhang9', '123456', '张九', 1, '2.jpg', 2, '2011-01-01', 2, '2022-10-27 16:35:33', '2022-10-27 16:36:25'),
       (27, 'zhang10', '123456', '张十', 1, '2.jpg', 2, '2004-01-01', 1, '2022-10-27 16:35:33', '2022-10-27 16:36:27'),
       (28, 'zhang11', '123456', '张十一', 1, '2.jpg', 2, '2007-01-01', 2, '2022-10-27 16:35:33',
        '2022-10-27 16:36:29'),
       (29, 'zhang12', '123456', '张十二', 1, '2.jpg', 2, '2020-01-01', 1, '2022-10-27 16:35:33',
        '2022-10-27 16:36:31');

-- 根据主键ID删除数据
delete
from tb_emp
where id = 17;

-- 插入数据
insert into tb_emp (username, name, gender, image, job, entrydate, dept_id, create_time, update_time)
values ('bobo', '啵啵', '1', '1.jpg', 1, '2005-01-01', 1, now(), now());

-- 更新数据,根据主键id
update tb_emp
set username    = ?,
    name        = ?,
    gender      = ?,
    image       = ?,
    job         = ?,
    entrydate   = ?,
    dept_id     = ?,
    update_time = now()
where id = ?;

-- 根据主键id查询数据
select *
from tb_emp
where id = 18;

select id,
       username,
       password,
       name,
       gender,
       image,
       job,
       entrydate,
       dept_id     as 'deptId', -- 这里给查询结果的字段起别名可以不用加 ''，只有中文需要加 ''
       create_time as createTime,
       update_time as updateTime
from tb_emp
where id = 1;


-- 条件查询数据
-- sql1
select *
from tb_emp
where name like '%张%'
  and gender = 1
  and entrydate between '2010-01-01' and '2020-01-01'
order by update_time desc;

-- concat 字符串拼接函数，把传入的参数全部拼接成一个字符串
select concat('hello', ' bobo', ' king'); -- 查询结果 hello bobo king
select concat('%', '张', '%');
-- 查询结果 %张%

-- sql2
select *
from tb_emp
where name like concat('%', '张', '%')
  and gender = 1
  and entrydate between '2010-01-01' and '2020-01-01'
order by update_time desc;

-- 以上两种sql1, sql2 查询结果一样

-- 批量删除，根据主键ID 29，30，31
delete
from tb_emp
where id in (29, 30, 31);


select *
from tb_emp;
update tb_emp
set image = '1.jpg';

select now();

select *
from tb_dept;
select *
from tb_emp;

-- 分页查询 limit 参数1, 参数2

-- 参数1: 起始索引 = (页码 - 1) * 每页展示记录数
-- 参数2: 查询返回记录数 = 每页展示记录数
-- 查询第一页数据，每页展示5条记录
select *
from tb_emp
limit 0,5;
-- 查询第二页数据，每页展示5条记录
select *
from tb_emp
limit 5,5;
-- 查询第三页数据，每页展示5条记录
select *
from tb_emp
limit 10,5;

-- 获取总记录数
select count(*)
from tb_emp;

select *
from tb_emp
where username = 'qinken';

select name
from tb_dept
where id = 17;

create table if not exists log
(
    id          bigint primary key auto_increment comment 'id',
    create_time datetime comment '日志操作时间',
    description varchar(300) comment '描述信息'
) charset utf8 comment '日志';

# select *
# from log;
#
# select id, create_time, description
# from log
# order by create_time desc
# limit 0, 5;
#
# select *
# from log
# where id in (1);
#
# select *
# from tb_emp;

insert into tb_emp (username, gender, dept_id, name, entrydate, job, image, create_time, update_time)
-- 第二个参数 1-2   第三个参数 1-7
values ('Jack', 1, 1, '杰克', '2018-05-07', 1,
        'https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/391f78b6-8167-488b-9c67-2a46e6e9865d.jpg', now(), now()),
       ('Tom', 1, 2, '汤姆', '2018-05-26', 2,
        'https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/391f78b6-8167-488b-9c67-2a46e6e9865d.jpg', now(), now()),
       ('Robin', 1, 2, '罗宾·', '2018-07-21', 1,
        'https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/391f78b6-8167-488b-9c67-2a46e6e9865d.jpg', now(), now()),
       ('John', 1, 3, '约翰', '2018-07-21', 1,
        'https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/391f78b6-8167-488b-9c67-2a46e6e9865d.jpg', now(), now()),
       ('Rose', 2, 4, '罗丝', '2018-07-21', 4,
        'https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/391f78b6-8167-488b-9c67-2a46e6e9865d.jpg', now(), now()),
       ('Alice', 2, 5, '爱丽丝', '2019-04-21', 1,
        'https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/391f78b6-8167-488b-9c67-2a46e6e9865d.jpg', now(), now()),
       ('Angela', 2, 1, '安吉拉', '2019-04-21', 4,
        'https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/391f78b6-8167-488b-9c67-2a46e6e9865d.jpg', now(), now()),
       ('Bonnie', 2, 2, '邦妮', '2019-06-05', 1,
        'https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/391f78b6-8167-488b-9c67-2a46e6e9865d.jpg', now(), now()),
       ('Cathy', 2, 3, '凯茜', '2019-06-05', 1,
        'https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/391f78b6-8167-488b-9c67-2a46e6e9865d.jpg', now(), now()),
       ('Sam', 1, 5, '山姆', '2019-11-08', 1,
        'https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/391f78b6-8167-488b-9c67-2a46e6e9865d.jpg', now(), now()),
       ('Porter', 1, 5, '波特', '2019-11-08', 2,
        'https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/391f78b6-8167-488b-9c67-2a46e6e9865d.jpg', now(), now()),
       ('Bridges', 1, 5, '布里吉斯', '2019-11-08', 1,
        'https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/391f78b6-8167-488b-9c67-2a46e6e9865d.jpg', now(), now()),
       ('carver', 1, 6, '卡文', '2020-05-02', 1,
        'https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/391f78b6-8167-488b-9c67-2a46e6e9865d.jpg', now(), now()),
       ('brett', 1, 6, '布雷特', '2020-05-02', 3,
        'https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/391f78b6-8167-488b-9c67-2a46e6e9865d.jpg', now(), now()),
       ('Dany', 2, 4, '丹妮', '2020-08-20', 1,
        'https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/391f78b6-8167-488b-9c67-2a46e6e9865d.jpg', now(), now()),
       ('Emily', 2, 7, '艾米丽', '2020-08-20', 1,
        'https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/391f78b6-8167-488b-9c67-2a46e6e9865d.jpg', now(), now()),
       ('Hellen', 2, 2, '海伦', '2020-08-20', 2,
        'https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/391f78b6-8167-488b-9c67-2a46e6e9865d.jpg', now(), now()),
       ('Gina', 2, 3, '吉娜', '2021-02-12', 1,
        'https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/391f78b6-8167-488b-9c67-2a46e6e9865d.jpg', now(), now()),
       ('Isabel', 2, 7, '伊莎贝尔', '2021-02-12', 1,
        'https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/391f78b6-8167-488b-9c67-2a46e6e9865d.jpg', now(), now()),
       ('Alan', 1, 3, '艾伦', '2021-09-08', 2,
        'https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/391f78b6-8167-488b-9c67-2a46e6e9865d.jpg', now(), now()),
       ('Barry', 1, 4, '巴里', '2021-09-08', 3,
        'https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/391f78b6-8167-488b-9c67-2a46e6e9865d.jpg', now(), now()),
       ('Eden', 1, 5, '伊登', '2022-01-17', 1,
        'https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/391f78b6-8167-488b-9c67-2a46e6e9865d.jpg', now(), now()),
       ('Felix', 1, 6, '菲力克斯', '2022-06-21', 4,
        'https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/391f78b6-8167-488b-9c67-2a46e6e9865d.jpg', now(), now()),
       ('Wade', 1, 7, '维德', '2022-06-21', 1,
        'https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/391f78b6-8167-488b-9c67-2a46e6e9865d.jpg', now(), now());

# desc tb_emp;
# desc tb_dept;
#
# select *
# from tb_dept;
#
# select *
# from tb_emp
# where name like '%张%'
#   and gender = 1
#   and entrydate between '2000-01-01' and '2015-12-31'
# order by update_time desc -- 按照更新时间降序排序
# limit 0,10;


-- if(条件表达式, true取值, false取值)
-- 如果gender=1，此时 gender字段的取值为 男性员工
-- 统计每个部门的男性、女性员工的数量
# select if(gender = 1, '男性员工', '女性员工') as '性别',
#        (case dept_id
#             when 1 then '人力资源部'
#             when 2 then '财务部'
#             when 3 then '销售部'
#             when 4 then '后勤部'
#             when 5 then '市场部'
#             when 6 then '生产部'
#             when 7 then '采购部'
#             else '非法部门' end)              as '部门',
#        count(*)                               as '数量'
# from tb_emp
# group by gender, dept_id; -- 按照性别、部门id先后分组


-- 统计所有部门名称
# select
#     (case id
#          when 1 then '人力资源部'
#          when 2 then '财务部'
#          when 3 then '销售部'
#          when 4 then '后勤部'
#          when 5 then '市场部'
#          when 6 then '生产部'
#          when 7 then '采购部'
#          else '非法部门' end) as '部门名'
# from tb_dept;

-- 统计每个部门男性员工的数量 在 tb_emp 员工表中 gender字段 1 男，2 女
# select count(*) as '各部门男性员工数量' from tb_emp where gender = 1 group by dept_id;

-- 统计每个部门女性员工的数量 在 tb_emp 员工表中 gender字段 1 男，2 女
# select count(*) as '各部门女性员工数量' from tb_emp where gender = 2 group by dept_id;


-- 统计每个部门的所有员工数量
# select
#     (case dept_id
#          when 1 then '人力资源部'
#          when 2 then '财务部'
#          when 3 then '销售部'
#          when 4 then '后勤部'
#          when 5 then '市场部'
#          when 6 then '生产部'
#          when 7 then '采购部'
#          else '非法部门' end) as '部门名',
#         count(*) as '员工数量'
# from tb_emp
# group by dept_id;


-- 统计每个部门男性员工数量
# select
#        (case dept_id
#             when 1 then '人力资源部'
#             when 2 then '财务部'
#             when 3 then '销售部'
#             when 4 then '后勤部'
#             when 5 then '市场部'
#             when 6 then '生产部'
#             when 7 then '采购部'
#             else '非法部门' end)              as '部门',
#        count(*)                               as '数量'
# from tb_emp
# where gender = 1   -- gender = 2 时统计每个部门女性员工数量
# group by dept_id; -- 按照性别、部门id先后分组

-- 员工职位信息统计
-- case 表达式 when 值1 then 结果1 when 值2 then 结果2 ...else.....end
# select (case job
#             when 1 then '班主任'
#             when 2 then '讲师'
#             when 3 then '学工主管'
#             when 4 then '教研主管'
#             else '未分配职位' end) as '职位',
#        count(*)
# from tb_emp
# group by job;


-- 用户表
create table if not exists user (
    `id` bigint primary key auto_increment comment 'id',
    `nickname` varchar(255) default '' comment '昵称',
    `username` varchar(255) not null unique comment '账号',
    `password` varchar(255) not null default '123456' comment '密码',
    `role` varchar(50) default '' comment '用户角色',
    `gender` char(4) comment '性别' default '',
    `age` int comment '年龄',
    `phone` varchar(50) default '' comment '电话',
    `address` varchar(255) default '' comment '地址',
    `email` varchar(255) default '' comment '邮箱',
    `avatar` varchar(300) default '' comment '头像',
    `register_date` datetime comment '注册日期'
) charset utf8 comment '用户';

insert into `user` values
(null, '云漪', 'cloudRipple', md5('twb4436520'), '管理员', '男', 22, '132****6651', '苏州市', '1493440094@qq.com', '', now());

select * from `user`;


