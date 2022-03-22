create table g_customer
(
    id       bigint not null,
    username varchar(255),
    password varchar(255),
    primary key (id)
);

create table g_customer_permission
(
    user_id    bigint not null,
    permission varchar(255)
);

alter table g_customer_permission
    add constraint fk_auth_user_permission
        foreign key (user_id)
            references g_customer;

create sequence hibernate_sequence start with 1 increment by 1;

INSERT INTO g_customer(id, username, password)
VALUES (NEXTVAL('hibernate_sequence'), 'admin',
        '$2a$12$KwhGOUehFkFW76tUwZ70KOPxH0aRZKQOzkHG9Nhxnxu8rtRrDruc2'); -- username: admin, password: admin

-- book-api permissions
INSERT INTO g_customer_permission(user_id, permission)
VALUES (1, 'create_book');
INSERT INTO g_customer_permission(user_id, permission)
VALUES (1, 'decrease_book_stock');
INSERT INTO g_customer_permission(user_id, permission)
VALUES (1, 'update_book_stock');
INSERT INTO g_customer_permission(user_id, permission)
VALUES (1, 'read_book');

-- order-api permissions
INSERT INTO g_customer_permission(user_id, permission)
VALUES (1, 'read_order');
INSERT INTO g_customer_permission(user_id, permission)
VALUES (1, 'create_order');