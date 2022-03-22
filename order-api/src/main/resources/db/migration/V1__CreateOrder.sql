create sequence hibernate_sequence start with 1 increment by 1;

create table order_book
(
    order_id bigint not null,
    book_id  bigint
);

create table t_order
(
    id          bigint       not null,
    create_date timestamp    not null,
    update_date timestamp,
    status      varchar(255),
    username    varchar(255) not null,
    primary key (id)
);

alter table order_book
    add constraint fk_order_book
        foreign key (order_id)
            references t_order;

create index t_order_username_index on t_order (username)