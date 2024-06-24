create table `order`
(
    id      bigint auto_increment
        primary key,
    user_id bigint null
);

create table order_item
(
    id        bigint auto_increment
        primary key,
    order_id  bigint      null,
    item_name varchar(32) null
);

create table undo_log
(
    branch_id     bigint       not null comment 'branch transaction id',
    xid           varchar(128) not null comment 'global transaction id',
    context       varchar(128) not null comment 'undo_log context,such as serialization',
    rollback_info longblob     not null comment 'rollback info',
    log_status    int          not null comment '0:normal status,1:defense status',
    log_created   datetime(6)  not null comment 'create datetime',
    log_modified  datetime(6)  not null comment 'modify datetime',
    constraint ux_undo_log
        unique (xid, branch_id)
)
    comment 'AT transaction mode undo table';

create index ix_log_created
    on undo_log (log_created);

