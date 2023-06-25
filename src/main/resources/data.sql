insert into user_details(id, birth_date, name)
values (100002, current_date(), 'Vikas');

insert into user_details(id, birth_date, name)
values (100003, current_date(), 'John');

insert into user_details(id, birth_date, name)
values (100004, current_date(), 'Cameron');

insert into post(id, description, user_id)
values(20001, 'I am learning spring', 100003);

insert into post(id, description, user_id)
values(20002, 'I will be learning dsa', 100002);

insert into post(id, description, user_id)
values(20003, 'I will be learning cs', 100002);

insert into post(id, description, user_id)
values(20004, 'I will be learning node', 100004);


insert into todo (id, username, description, target_date, done)
values (1001, 'vikas','Learn Spring Boot', CURRENT_DATE(), false);
insert into todo (id, username, description, target_date, done)
values (1002, 'vikas','Learn Database', CURRENT_DATE(), false);
insert into todo (id, username, description, target_date, done)
values (1003, 'vikas','Learn Something New', CURRENT_DATE(), false);
insert into todo (id, username, description, target_date, done)
values (1004, 'vikas','Learn DSA', CURRENT_DATE(), false);
insert into todo (id, username, description, target_date, done)
values (1005, 'nick','Learn Network', CURRENT_DATE(), false);
insert into todo (id, username, description, target_date, done)
values (1006, 'nick','Learn computer science', CURRENT_DATE(), false);