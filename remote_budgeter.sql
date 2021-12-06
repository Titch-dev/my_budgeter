
-- create database 
drop database if exists my_budgeter;
create database my_budgeter;

-- switch to database
use my_budgeter;

-- create customer table
drop table if exists customer;
create table customer (
	id int primary key auto_increment,
    first_name varchar(25) not null,
    email varchar(25) not null,
    bal_name varchar(25) not null default "My Balance",
    bal_start datetime not null default current_timestamp,
    current_bal decimal(8,2) not null default 0.00,
    current_in decimal(8,2) not null default 0.00,
    current_out decimal(8,2) not null default 0.00,
    bal_goal decimal(8,2) not null default 0.00
);

-- create transactions table
create table transactions(
	id int primary key auto_increment,
    direction enum ("in", "out"),
    category enum ("savings", "leisure", "shelter", "transport", "food", "credit", "buffer", "goal", "salary", "investments", "other"),
    sub_category varchar (25) null,
    trans_date datetime not null default current_timestamp,
    end_date datetime null,
	trans_amount decimal(8,2) not null default 0.00,
    frequency enum ("day", "week", "bi-week", "month", "bi-month", "quater", "half", "year", "once") not null default "month",
    customer_id int not null,
    constraint fk_outgoing_customer_id
		foreign key (customer_id)
        references customer(id)
);

-- create goal table
drop table if exists goal;
create table goal (
	id int primary key auto_increment,
    `name` varchar (25) not null,
    balance decimal(8,2) not null default 0.00,
    target_amount decimal(8,2) not null,
    target_date date null,
    customer_id int not null,
    constraint fk_goal_customer_id
		foreign key (customer_id)
        references customer(id)
);