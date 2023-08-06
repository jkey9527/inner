create table inner_user
(
    user_id       varchar(36) not null comment '用户主键'
        primary key,
    user_no       varchar(255) null comment '用户编号',
    user_name     varchar(255) null comment '用户名称',
    user_phone    varchar(11) null comment '用户手机号',
    user_password varchar(255) null comment '用户密码',
    user_type     int(1) null comment '用户类型'
) comment '用户表';

create table inner_product
(
    pro_id         varchar(36) not null
        primary key,
    pro_no         varchar(36),
    pro_name       varchar(36),
    pro_type       varchar(36),
    pro_real_price decimal(10, 2),
    pro_price      decimal(10, 2),
    pro_sell_price decimal(10, 2)
) comment '货品表';

create table inner_product_detail
(
    pro_det_id    varchar(36) not null
        primary key,
    pro_main_id   varchar(36),
    pro_det_color varchar(36),
    pro_det_size  varchar(36),
    pro_det_num   int
) comment '货品明细表';

create table inner_record
(
    r_id    varchar(36) not null
        primary key,
    r_pro_id   varchar(36),
    r_user_id  varchar(36),
    r_date  datetime,
    r_week  int,
    r_month  int,
    r_year  int,
    r_type  varchar(36),
    r_sell_price  decimal(10, 2),
    r_sell_num  int
) comment '销售明细表';

create table inner_cost
(
    cost_id    varchar(36) not null
        primary key,
    cost_pro   varchar(36),
    cost_user_id  varchar(36),
    cost_date  datetime,
    cost_type  varchar(36),
    cost_money  decimal(10, 2)
) comment '费用表';

create table inner_settlement
(
    s_id    varchar(36) not null
        primary key,
    s_month   varchar(36),
    s_coll_money  decimal(10, 2),
    s_pay_money  decimal(10, 2),
    s_money  decimal(10, 2)
) comment '结算表';

create table inner_log
(
    log_id    varchar(36) not null
        primary key,
    log_model   varchar(36),
    log_type   varchar(36),
    log_info   text,
    log_memo   varchar(999),
    log_user_id   varchar(36),
    log_date  datetime
) comment '日志表';

create table inner_system
(
    sys_id    int(11) not null comment '主键',
    sys_code  varchar(255) comment '参数编码',
    sys_value varchar(255) comment '参数值',
    sys_label varchar(255) comment '描述',
    primary key (sys_id)
) comment '系统参数表';

insert into inner_system
values (1, 'submit_cost', 'false', '允许二次提交费用');
insert into inner_system
values (2, 'show_user', 'false', '展示用户信息');