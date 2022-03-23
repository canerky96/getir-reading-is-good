create table g_order_book
(
    id               bigint       not null,
    create_date      timestamp    not null,
    created_by       varchar(255) not null,
    last_modified_by varchar(255),
    update_date      timestamp,
    name             varchar(255),
    price            bigint       not null,
    writer           varchar(255),
    order_id         bigint       not null,
    primary key (id)
);


alter table g_order_book
    add constraint fk_order_book_order_id
        foreign key (order_id)
            references g_order;