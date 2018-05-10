--drop user doc_manager@localhost;
--flush privileges;
CREATE USER doc_manager@localhost identified by '123123';
GRANT usage ON *.* TO doc_manager@localhost identified by '123123';
CREATE DATABASE IF NOT exists citizendb;
grant all privileges on citizendb.* to doc_manager@localhost;
use citizendb;
create table citizens
(	
id int primary key auto_increment,
first_name varchar(50),
middle_name varchar(50),
last_name varchar(50),
birth_day date,
address varchar(200)) engine=InnoDB  charset=utf8;

create table documents
(	
id int primary key auto_increment,
type varchar(50),
holder_id int,
authority varchar(100),
issue_date date,
expiry_date date,
constraint holder_id_fk foreign key (holder_id) references citizens (id) on delete cascade on update cascade) engine=InnoDB  charset=utf8;

