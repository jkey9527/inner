create table cattle_house.house_contract
(
    con_id            varchar(36)                 not null comment '合同主键'
        primary key,
    con_no            varchar(32)                 null comment '合同编号',
    con_start_date    date                        null comment '合同开始日期',
    con_end_date      date                        null comment '合同结束日期',
    con_year          int(2)                      null comment '合同签订年限',
    con_house_type    int(2)                      null comment '房屋类型',
    con_house_no      int(3)                      null comment '房间号',
    con_month_money   decimal(10, 2) default 0.00 null comment '每月租金',
    con_deposit_money decimal(10, 2) default 0.00 null comment '押金',
    con_paid_money    decimal(10, 2) default 0.00 null comment '已付租金',
    con_pay_money     decimal(10, 2) default 0.00 null comment '待付租金',
    con_total_money   decimal(10, 2) default 0.00 null comment '总租金',
    con_key_num       int(2)                      null comment '钥匙数',
    con_w_price       decimal(10, 2) default 0.00 null comment '水单价',
    con_e_price       decimal(10, 2) default 0.00 null comment '电单价',
    con_g_price       decimal(10, 2) default 0.00 null comment '气单价',
    con_state         int(2)                      null comment '合同状态'
);

create table cattle_house.house_user
(
    user_id       varchar(36)  not null comment '用户主键'
        primary key,
    user_no       varchar(255) null comment '用户编号',
    user_name     varchar(255) null comment '用户名称',
    user_phone    varchar(11)  null comment '用户手机号',
    user_password varchar(255) null comment '用户密码',
    user_type     int(1)       null comment '用户类型',
    user_house_no int(3)       null comment '房间号',
    user_state    int(1)       null comment '用户状态'
);

