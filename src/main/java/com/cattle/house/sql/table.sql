create table house_contract
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
    con_state         int(2)                      null comment '合同状态',
    con_notes         text                        null comment '备注'
)
    comment '合同表';

create table house_cost
(
    cost_id          varchar(36)                 not null comment '费用主键'
        primary key,
    cost_contract_no varchar(32)                 null comment '合同编号',
    cost_times       int(4)                      null comment '抄表次数',
    cost_w_s_number  decimal(10, 2) default 0.00 null comment '水期初读数',
    cost_w_e_number  decimal(10, 2) default 0.00 null comment '水期末读数',
    cost_e_s_number  decimal(10, 2) default 0.00 null comment '电期初读数',
    cost_e_e_number  decimal(10, 2) default 0.00 null comment '电期末读数',
    cost_g_s_number  decimal(10, 2) default 0.00 null comment '气期初读数',
    cost_g_e_number  decimal(10, 2) default 0.00 null comment '气期末读数',
    cost_w_money     decimal(10, 2) default 0.00 null comment '水本期费用',
    cost_e_money     decimal(10, 2) default 0.00 null comment '电本期费用',
    cost_g_money     decimal(10, 2) default 0.00 null comment '气本期费用',
    cost_total_money decimal(10, 2) default 0.00 null comment '本期费用合计',
    cost_date        date                        null comment '抄表日期',
    cost_w_number    decimal(10, 2) default 0.00 null comment '水本期读数',
    cost_e_number    decimal(10, 2) default 0.00 null comment '电本期读数',
    cost_g_number    decimal(10, 2) default 0.00 null comment '气本期读数'
)
    comment '费用表';

create table house_user
(
    user_id          varchar(36)  not null comment '用户主键'
        primary key,
    user_no          varchar(255) null comment '用户编号',
    user_name        varchar(255) null comment '用户名称',
    user_phone       varchar(11)  null comment '用户手机号',
    user_password    varchar(255) null comment '用户密码',
    user_type        int(1) null comment '用户类型',
    user_contract_no varchar(32) null comment '合同号',
    user_state       int(1) null comment '用户状态',
    user_id_card     varchar(20) null comment '身份证号',
    user_car_no      varchar(255) null comment '车牌号',
    user_notes       text null comment '备注',
    user_key_no      int(2) null comment '密码锁编号'
) comment '用户表';

create table house_record
(
    r_id          varchar(36) not null
        primary key,
    r_type        int null,
    r_money       decimal(10, 2) null,
    r_date        date null,
    r_msg         varchar(255) null,
    r_create_time datetime null
) comment '明细表';