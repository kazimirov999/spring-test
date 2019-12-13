drop  table if exists   order_items;
drop  table if exists orders;
drop  table if exists comments;
drop  table if exists user_role;
drop  table if exists roles;
drop  table if exists users;
drop  table if exists product_category;
drop  table if exists products;
drop  table if exists categories;

create sequence if not exists hibernate_sequence start with 100;

create table categories
(
    id    SERIAL,
    name varchar(255),
    created_by varchar(255),
    creation_date timestamp,
    last_modified_by varchar(255),
    last_modified_date timestamp,

    constraint category_pk
        primary key (id)
);

create table products
(
    id           SERIAL,
    name        varchar(255),
    description varchar(255),
    price decimal,
    created_by varchar(255),
    creation_date timestamp,
    last_modified_by varchar(255),
    last_modified_date timestamp,

    constraint product_pk
        primary key (id)
);

create table  product_category
(
    product_id  int,
    category_id int ,
    created_by varchar(255),
    creation_date timestamp,
    last_modified_by varchar(255),
    last_modified_date timestamp,

    constraint product_category_pk
        primary key (product_id, category_id),
    constraint product_category_categories_fk
        foreign key (category_id) references categories (id) on delete cascade,
    constraint product_category_products_fk
        foreign key (product_id) references products (id) on delete cascade
);

create table  roles
(
    id    SERIAL,
    name varchar(255),
    created_by varchar(255),
    creation_date timestamp,
    last_modified_by varchar(255),
    last_modified_date timestamp,

    constraint role_pk
        primary key (id)
);

create table  users
(
    id     SERIAL,
    name  varchar(255),
    email varchar(255),
    created_by varchar(255),
    creation_date timestamp,
    last_modified_by varchar(255),
    last_modified_date timestamp,

    constraint user_pk
        primary key (id)
);

create table user_role
(
    user_id int,
    role_id int,
    created_by varchar(255),
    creation_date timestamp,
    last_modified_by varchar(255),
    last_modified_date timestamp,

    constraint user_role_pk
        primary key (user_id, role_id),
    constraint user_role_roles_fk
        foreign key (role_id) references roles (id) on delete  cascade ,
    constraint user_role_users_fk
        foreign key (user_id) references users (id) on update  cascade
);

create table comments
(
    id          SERIAL,
    text       varchar(255),
    user_id    int,
    product_id int ,
    comment_id int,
    created_by varchar(255),
    creation_date timestamp,
    last_modified_by varchar(255),
    last_modified_date timestamp,

    constraint comment_pk
        primary key (id),
    constraint comments_users_fk
        foreign key (user_id) references users (id),
    constraint comments_products_fk
        foreign key (product_id) references products (id),
    constraint comments_comments_fk
        foreign key (comment_id) references comments (id)
);

create table  orders
(
    id        SERIAL,
    order_date date,
    client_id int,
    created_by varchar(255),
    creation_date timestamp,
    last_modified_by varchar(255),
    last_modified_date timestamp,

    constraint order_pk
        primary key (id),
    constraint orders_users_fk
        foreign key (client_id) references users (id)
);

create table  order_items
(
    id   SERIAL ,
    order_id   int,
    product_id int,
    quantity   int not null,
    created_by varchar(255),
    creation_date timestamp,
    last_modified_by varchar(255),
    last_modified_date timestamp,

    constraint order_item_pk
        primary key (id),
    constraint order_items_orders_fk
        foreign key (order_id) references orders (id),
    constraint order_items_products_fk
        foreign key (product_id) references products (id)
);
