CREATE TABLE tb_cable(
	cableid int primary key not null auto_increment,
	cablemodel varchar(40),
	cablestandard varchar(40),
	remark varchar(200),
	available int
);

create table tb_order(
	orderid int primary key not null auto_increment,
	inorout int,
	companyname varchar(80),
	contacts varchar(20),
	telephone varchar(40),
	address varchar(80),
	warehouse varchar(80),
	ordertime datetime,
	totalprice float,
	remark varchar(200),
	available int
);

create table tb_orderitem(
	orderitemid int primary key not null auto_increment,
	orderid int,
	cablemodel varchar(40),
	cablestandard varchar(40),
	quality varchar(40),
	unit varchar(10),
	color varchar(10),
	number float,
	price float,
	discount float,
	total float,
	remark varchar(200),
	available int
);

create table tb_storage(
	storageid int primary key not null auto_increment,
	cablemodel varchar(40),
	cablestandard varchar(40),
	unit varchar(10),
	number float,
	quality varchar(40),
	color varchar(10),
	providername varchar(80),
	storagetime datetime,
	address varchar(80),
	remark varchar(200),
	available int
);

create table tb_provider(
	providerid int primary key not null auto_increment,
	providername varchar(80),
	contacts varchar(20),
	telephone varchar(40),
	address varchar(80),
	warehouse varchar(80),
	remark varchar(200),
	available int
);

create table tb_customer(
	customerid int primary key not null auto_increment,
	customername varchar(80),
	contacts varchar(20),
	telephone varchar(40),
	address varchar(80),
	warehouse varchar(80),
	remark varchar(200),
	available int
);

create table tb_quality(
	qualityid int primary key not null auto_increment,
	quality varchar(20),
	available int
);

create table tb_unit(
	unitid int primary key not null auto_increment,
	unit varchar(20),
	available int
);

create table tb_color(
	colorid int primary key not null auto_increment,
	color varchar(20),
	available int
);

create table tb_userinfo(
	userid int primary key not null auto_increment,
	username varchar(20),
	password varchar(20),
	available int
);
