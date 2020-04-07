
create sequence if not exists hibernate_sequence start with 10 increment by 1;

create table if not exists users
(
id int not null,
email varchar(255),
name varchar(255),
created_by varchar(255),
creation_date timestamp,
last_modified_by varchar(255),
last_modified_date timestamp,
primary key (id)
);


create table if not exists orders
(
id int not null,
user_id int,
created_by varchar(255),
creation_date timestamp,
last_modified_by varchar(255),
last_modified_date timestamp,
primary key (id),
constraint  orders_user_id_fk foreign key (user_id) references users (id)
);

create table if not exists products
(
id int not null,
name varchar(255),
price int,
order_id int,
created_by varchar(255),
creation_date timestamp,
last_modified_by varchar(255),
last_modified_date timestamp,
primary key (id),
constraint products_order_id_fk foreign key (order_id) references orders (id)
);

create table if not exists comments
(
id integer not null,
parent int,
text varchar(255),
product_id int,
user_id int,
created_by varchar(255),
creation_date timestamp,
last_modified_by varchar(255),
last_modified_date timestamp,
primary key (id),

constraint comments_comments_id_fk foreign key (parent) references comments (id),
constraint comments_product_id_fk foreign key (product_id) references products (id),
constraint comments_user_id_fk foreign key (user_id) references users (id)

);

create table if not exists roles
(
id int not null,
name varchar(255),
created_by varchar(255),
creation_date timestamp,
last_modified_by varchar(255),
last_modified_date timestamp,
primary key (id)
);

create table if not exists user_role
(
user_id int not null,
role_id int not null,
created_by varchar(255),
creation_date timestamp,
last_modified_by varchar(255),
last_modified_date timestamp,
primary key (user_id, role_id),

constraint user_role_role_fk foreign key (role_id) references roles (id) on delete cascade,
constraint user_role_user_fk foreign key (user_id) references users (id)
);

create table if not exists order_item
(
id int,
order_id int not null,
product_id int not null,
quantity int,
created_by varchar(255),
creation_date timestamp,
last_modified_by varchar(255),
last_modified_date timestamp,
primary key (order_id, product_id),

constraint order_item_orders_fk foreign key (order_id) references orders (id) on update cascade,
constraint order_item_products_fk foreign key (product_id) references products (id) on update cascade
);