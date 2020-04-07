-- insert into orders (user_id, order_id) values (?, ?)
--
-- insert into user (email, experience, name, user_id) values (?, ?, ?, ?)
--
-- insert into product (name, order_id, price, id) values (?, ?, ?, ?)
--
-- insert into comment (product_id, sub_comment, text, id) values (?, ?, ?, ?)
-- insert into comments (product_id, sub_comment, text, user_id, id) values (?, ?, ?, ?, ?)

insert into users (email, name, id) values ('max@gmail.com', 'Max', 1);
insert into users (email, name, id) values ('philip@gmail.com', 'Philip', 2);
insert into users (email, name, id) values ('richard@gmail.com', 'Richard', 3);

insert into products (name, price, id) values ('Opel', 5000, 1);
insert into products (name, price, id) values ('Volvo', 6000, 2);
insert into products (name, price, id) values ('Audi', 4000, 3);

insert into comments (id, parent, text, user_id, product_id) values (1, null , 'best price', 1, 1);
insert into comments (id, parent, text, user_id, product_id) values (2, 1, 'good car', 2,2);
insert into comments (id, parent, text, user_id, product_id) values (3, 2, 'car', 3,3);

insert into orders (user_id, id) values (1, 1);
insert into orders (user_id, id) values (2, 2);
insert into orders (user_id, id) values (3, 3);

insert into roles (id, name) values (1, 'admin');
insert into roles (id, name) values (2, 'user');
insert into roles (id, name) values (3, 'client');

insert into user_role (user_id, role_id) values (1,1);
insert into user_role (user_id, role_id) values (2,2);
insert into user_role (user_id, role_id) values (3,3);

insert into order_item (id, order_id, product_id, quantity) values (1,1,1,1);
insert into order_item (id, order_id, product_id, quantity) values (2,2,2,2);
insert into order_item (id, order_id, product_id, quantity) values (3,3,3,3);





