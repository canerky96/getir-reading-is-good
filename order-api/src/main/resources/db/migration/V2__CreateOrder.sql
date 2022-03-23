create table g_order
(
    id               bigint       not null,
    create_date      timestamp    not null,
    created_by       varchar(255) not null,
    last_modified_by varchar(255),
    update_date      timestamp,
    status           varchar(255),
    username         varchar(255) not null,
    primary key (id)
);

create index index_g_order_username on g_order (username);
