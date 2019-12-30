drop  table if exists   order_items;
drop  table if exists orders;
drop  table if exists comments;
drop  table if exists user_role;
drop  table if exists roles;
drop  table if exists product_category;
drop  table if exists products;
drop  table if exists categories;
drop  table if exists users;


create sequence if not exists hibernate_sequence start with 100;
create table  users
(
    id    serial,
    name  varchar(255),
    email varchar(255) unique,
    password varchar(255) ,
    created_by varchar(255),
    creation_date timestamp,
    last_modified_by varchar(255),
    last_modified_date timestamp,

    constraint user_pk
        primary key (id),
    constraint users_users_created_fk
        foreign key (created_by) references users (email),
    constraint users_users_modified_fk
        foreign key (last_modified_by) references users (email)
);

create table categories
(
    id   serial,
    name varchar(255),
    created_by varchar(255),
    creation_date timestamp,
    last_modified_by varchar(255),
    last_modified_date timestamp,

    constraint category_pk
        primary key (id),
    constraint categories_users_created_fk
        foreign key (created_by) references users (email),
    constraint categories_users_modified_fk
        foreign key (last_modified_by) references users (email)
);

create table products
(
    id          serial,
    name        varchar(255),
    description varchar(255),
    price decimal,
    created_by varchar(255),
    creation_date timestamp,
    last_modified_by varchar(255),
    last_modified_date timestamp,

    constraint product_pk
        primary key (id),
    constraint products_users_created_fk
        foreign key (created_by) references users (email),
    constraint products_users_modified_fk
        foreign key (last_modified_by) references users (email)
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
        foreign key (product_id) references products (id) on delete cascade,
    constraint product_category_users_created_fk
        foreign key (created_by) references users (email),
    constraint product_category_users_modified_fk
        foreign key (last_modified_by) references users (email)
);

create table  roles
(
    id   serial,
    name varchar(255),
    created_by varchar(255),
    creation_date timestamp,
    last_modified_by varchar(255),
    last_modified_date timestamp,

    constraint role_pk
        primary key (id),
    constraint roles_users_created_fk
        foreign key (created_by) references users (email),
    constraint roles_users_modified_fk
        foreign key (last_modified_by) references users (email)
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
        foreign key (user_id) references users (id) on update  cascade,
    constraint user_role_users_created_fk
        foreign key (created_by) references users (email),
    constraint user_role_users_modified_fk
        foreign key (last_modified_by) references users (email)
);

create table comments
(
    id         serial,
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
        foreign key (comment_id) references comments (id),
    constraint comments_users_created_fk
        foreign key (created_by) references users (email),
    constraint comments_users_modified_fk
        foreign key (last_modified_by) references users (email)
);

create table orders
(
    id        serial,
    order_date date,
    client_id int,
    created_by varchar(255),
    creation_date timestamp,
    last_modified_by varchar(255),
    last_modified_date timestamp,

    constraint order_pk
        primary key (id),
    constraint orders_users_fk
        foreign key (client_id) references users (id),
    constraint orders_users_created_fk
        foreign key (created_by) references users (email),
    constraint orders_users_modified_fk
        foreign key (last_modified_by) references users (email)
);

create table  order_items
(
    id   serial,
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
        foreign key (product_id) references products (id),
    constraint order_item_users_created_fk
        foreign key (created_by) references users (email),
    constraint order_item_users_modified_fk
        foreign key (last_modified_by) references users (email)
);

insert into roles(id, name) values ( 1, 'admin' );
insert into users(id, name, email, password) values ( 1, 'admin', 'admin', '$2a$10$Ym8aG/LB87KgyaNMYNsz0eY3g17BJNw0MXmsnHzAlbWHMIcGXH4T2');
insert into user_role(user_id, role_id) values ( 1, 1 );
