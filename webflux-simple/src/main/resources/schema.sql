drop table t_book if exists;
drop table t_order if exists;
drop table t_order_book if exists;

create table t_book (
    id bigint auto_increment,
    create_time timestamp,
    update_time timestamp,
    title varchar(255),
    price bigint,
    primary key (id)
);

create table t_order (
    id bigint auto_increment,
    create_time timestamp,
    update_time timestamp,
    customer varchar(255),
    state integer not null,
    primary key (id)
);

create table t_order_book (
    book_order_id bigint not null,
    items_id bigint not null
);
