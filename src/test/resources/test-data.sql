insert into categories(id, name) values ( 1, 'car');
insert into categories(id, name) values ( 2, 'bike');
insert into categories(id, name) values ( 3, 'electric');
insert into categories(id, name) values ( 4, 'unusable');

insert into products(id, name, description, price) values ( 1, 'Tesla', 'Tesla Model S', 40000);
insert into products(id, name, description, price) values ( 2, 'Mazda', 'Mazda CX-5', 35000);
insert into products(id, name, description, price) values ( 3, 'Giant', 'Giant', 5000);

insert into product_category(product_id, category_id) values ( 1, 1 );
insert into product_category(product_id, category_id) values ( 1, 3 );
insert into  product_category(product_id, category_id) values ( 2, 1 );
insert into  product_category(product_id, category_id) values ( 3, 2 );

insert into roles(id, name) values ( 1, 'client' );
insert into roles(id, name) values ( 2, 'admin' );
insert into roles(id, name) values ( 3, 'user' );

insert into users(id, name, email) values ( 1, 'John', 'john@gmail.com' );
insert into users(id, name, email) values ( 2, 'Vasya', 'vasya@gmail.com' );

insert into user_role(user_id, role_id) values ( 1, 1 );
insert into user_role(user_id, role_id) values ( 1, 2 );
insert into user_role(user_id, role_id) values ( 2, 1 );


insert into orders(id, order_date, client_id) values ( 1,'2019-11-11', 1 );
insert into orders(id, order_date, client_id) values ( 2, '2019-11-12', 2 );

insert into order_items(id, order_id, product_id, quantity) values (1, 1, 1, 1);
insert into order_items(id, order_id, product_id, quantity) values (2, 2, 3, 2);

insert into comments(id, text, user_id, product_id) values ( 1, 'First comment', 1, 1);
insert into comments(id, text, user_id, product_id) values ( 3, 'Some comment', 2, 3);
insert into comments(id, text, user_id, comment_id) values ( 2, 'Sub comment', 2, 1);