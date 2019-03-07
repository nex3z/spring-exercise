create table t_book (
    id bigint not null auto_increment,
    title varchar(255),
    price bigint not null,
    create_time timestamp,
    update_time timestamp,
    primary key (id)
);