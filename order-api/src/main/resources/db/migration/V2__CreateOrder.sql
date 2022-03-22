create table g_order
(
    id          bigint       not null,
    create_date timestamp    not null,
    update_date timestamp,
    status      varchar(255),
    username    varchar(255) not null,
    primary key (id)
);

create index index_g_order_username on g_order (username);
