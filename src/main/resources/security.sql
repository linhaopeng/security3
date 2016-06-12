drop database if exists security3;
create database security3 default character set utf8;

use security3;

drop table if exists sys_role_privilege;
drop table if exists sys_user_role;

drop table if exists sys_role;
drop table if exists sys_user;
drop table if exists sys_url;
drop table if exists sys_privilege;

create table sys_user
(
   id                  int not null auto_increment,
   login               varchar(20),
   name                varchar(20),
   pass                varchar(32),
   primary key (id)
);

create table sys_role
(
	id int not null auto_increment,
	name varchar(200),
	detail varchar(200),
	primary key(id)
);


create table sys_privilege
(
    id int not null auto_increment,
    name varchar(200),
    primary key(id)
);
/* sys_privilegege对应多个url地址  */
create table sys_url
(
    id int not null auto_increment,
    url varchar(200),
    pid int,
    primary key(id)
);

create table sys_role_privilege
(
	pid int,
	rid int,
	primary key(rid,pid)
);

create table sys_user_role(
	aid int,
	rid int,
	primary key(aid,rid)
);
/******************添加用户与角色测试数据*************************/
INSERT INTO sys_user (login,name,pass) VALUES ('test01','张三','e10adc3949ba59abbe56e057f20f883e');
INSERT INTO sys_user (login,name,pass) VALUES ('test02','李四','e10adc3949ba59abbe56e057f20f883e');
INSERT INTO sys_role (name,detail) VALUES ('ADMIN','管理员账户');
INSERT INTO sys_role (name,detail) VALUES ('USER','普通账户');
INSERT INTO sys_user_role (aid,rid) VALUES (1,1);
INSERT INTO sys_user_role (aid,rid) VALUES (2,2);
/****************************************************************/

/******************添加资源与角色测试数据*************************/
INSERT INTO sys_privilege (name) VALUES ('管理员页面');
INSERT INTO sys_privilege (name) VALUES ('用户页面');

INSERT INTO sys_url (url,pid) VALUES ('/admin/save.jsp',1);
INSERT INTO sys_url (url,pid) VALUES ('/admin/save2.jsp',1);
INSERT INTO sys_url (url,pid) VALUES ('/user/save.jsp',2);
INSERT INTO sys_url (url,pid) VALUES ('/user/save2.jsp',2);

INSERT INTO sys_role_privilege (pid,rid) VALUES (1,1);
INSERT INTO sys_role_privilege (pid,rid) VALUES (2,1);
INSERT INTO sys_role_privilege (pid,rid) VALUES (2,2);
/****************************************************************/

SELECT * FROM sys_user_role;
SELECT * FROM sys_role_privilege;
SELECT * FROM sys_user;
SELECT * FROM sys_role;
SELECT * FROM sys_privilege;
SELECT * FROM sys_url;
