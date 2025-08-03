insert into roles (name) values ('ROLE_USER');
insert into roles (name) values ('ROLE_ADMIN');

insert into users (email, password) values ('user@example.com', 'password123');
insert into users (email, password) values ('admin@example.com', 'password123');

insert into users_roles (user_id, role_id) values (1, 1);
insert into users_roles (user_id, role_id) values (2, 2);