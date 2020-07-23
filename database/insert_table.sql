use newspringboot122019;

insert into role(code,name) values('MANAGER','MANAGER');
insert into role(code,name) values('STAFF','STAFF');

insert into user(username,password,fullname,status)
values('nguyenvana','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','nguyen van a',1);
insert into user(username,password,fullname,status)
values('nguyenvanb','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','nguyen van b',1);
insert into user(username,password,fullname,status)
values('nguyenvanc','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','nguyen van c',1);
insert into user(username,password,fullname,status)
values('nguyenvand','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','nguyen van d',1);

INSERT INTO user_role(user_id,role_id) VALUES (1,1);
INSERT INTO user_role(user_id,role_id) VALUES (2,2);
INSERT INTO user_role(user_id,role_id) VALUES (3,2);
INSERT INTO user_role(user_id,role_id) VALUES (4,2);

insert into building(name,numberofbasement,floorarea,district,ward,street,rentcost,costdescription,managername,managerphone,type) values('FCL Building',2,500,'QUAN_1','Phường 2','59 Pham Xích Long',15,'15 triệu/m2','Hùng','012345678','TANG_TRIET,NGUYEN_CAN');
insert into building(name,numberofbasement,floorarea,district,ward,street,rentcost,costdescription,managername,managerphone,type) values('ACM Tower',2,650,'QUAN_2','Phường 4','96 Cao Thắng',18,'18 triệu/m2','Cường','012345678','NGUYEN_CAN');
insert into building(name,numberofbasement,floorarea,district,ward,street,rentcost,costdescription,managername,managerphone,type) values('Alpha 2 Building',1,200,'QUAN_3','Phường 6','153 Nguyễn Đình Chiểu',20,'20 triệu/m2','Huy','012345678','TANG_TRIET,NGUYEN_CAN,NOI_THAT');
insert into building(name,numberofbasement,floorarea,district,ward,street,rentcost,costdescription,managername,managerphone,type) values('IDD 1 Building',1,200,'QUAN_4','Phường 7','111 Lý Chính Thắng',12,'12 triệu/m2','Vy','012345678','NOI_THAT');

INSERT INTO assignmentbuilding(building_id,staff_id) VALUES (1,2);
INSERT INTO assignmentbuilding(building_id,staff_id) VALUES (1,3);
INSERT INTO assignmentbuilding(building_id,staff_id) VALUES (2,4);
