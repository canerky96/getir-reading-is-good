create sequence hibernate_sequence start with 1 increment by 1;

create table g_book
(
    id               bigint       not null,
    create_date      timestamp    not null,
    created_by       varchar(255) not null,
    last_modified_by varchar(255),
    update_date      timestamp,
    name             varchar(255),
    writer           varchar(255),
    price            bigint       not null,
    stock            integer      not null,
    version          integer,
    primary key (id)
);

INSERT INTO g_book(id, create_date, created_by, last_modified_by, update_date, name, writer, price, stock, version)
VALUES (NEXTVAL('hibernate_sequence'), NOW(), 'SYSTEM', 'SYSTEM', NULL, 'Yabancı', 'Albert Camus', 22.1, 10, 0);

INSERT INTO g_book(id, create_date, created_by, last_modified_by, update_date, name, writer, price, stock, version)
VALUES (NEXTVAL('hibernate_sequence'), NOW(), 'SYSTEM', 'SYSTEM', NULL, 'Martı', 'Anton Çehov', 12.5, 20, 0);
