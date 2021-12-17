-- create test database 
drop database if exists my_budgeter;
create database my_budgeter;

-- switch to database
use my_budgeter;

-- create tables and relationships
drop table if exists customer;
create table customer (
	id int primary key auto_increment,
    first_name varchar(25) not null,
    email varchar(25) not null,
    bal_name varchar(25) default "My Balance",
    bal_start date null default (current_date),
    current_bal decimal(8,2) default 0.00,
    current_in decimal(8,2) default 0.00,
    current_out decimal(8,2) default 0.00,
    bal_goal decimal(8,2) default 0.00,
    bal_savings decimal(8,2) default 0.00
);

drop table if exists budget;
create table budget(
	id int primary key auto_increment,
    budget_name enum ("savings", "leisure", "shelter", "transport", "food", "credit", "goal", "salary", "investments", "other") not null
);

drop table if exists transactions;
create table transactions(
	id int primary key auto_increment,
    direction enum ("in", "out") not null,
    budget_id int not null,
    sub_category varchar (25) null,
    trans_date date not null default (current_date),
    end_date date null,
	trans_amount decimal(8,2) null default 0.00,
    frequency enum ("day", "week", "bi-week", "month", "bi-month", "quater", "half", "year", "once") not null default "month",
    customer_id int not null,
    constraint fk_transactions_budget_id
		foreign key (budget_id)
        references budget(id),
    constraint fk_transactions_customer_id
		foreign key (customer_id)
        references customer(id)
        on delete cascade
);

drop table if exists customer_budget;
create table customer_budget(
	customer_id int not null,
    budget_id int not null,
    accurate_balance decimal(8,2) default 0.00,
    user_def_balance decimal(8,2) default 0.00,
    constraint pk_customer_id_category_name
		primary key (customer_id, budget_id),
	constraint fk_budgets_customer_id
		foreign key (customer_id)
        references customer(id)
        on delete cascade,
	constraint fk_budgets_category_name
		foreign key (budget_id)
        references budget(id)
);

drop table if exists goal;
create table goal (
	id int primary key auto_increment,
    goal_name varchar (25) not null,
    balance decimal(8,2) not null default 0.00,
    target_amount decimal(8,2) not null,
    target_date date null,
    achieved boolean default false,
    customer_id int not null,
    constraint fk_goal_customer_id
		foreign key (customer_id)
        references customer(id)
        on delete cascade
);
SET SQL_SAFE_UPDATES = 0;

-- removing any other existing data
	
    insert into customer (first_name, email, bal_name, bal_start, current_bal, 
							current_in, current_out, bal_goal, bal_savings)
				values ("joe", "j@jmail.com", default, "2021-12-15", 420.00, 2220.00, 1600.00, 425.00, 100.00);
                
	insert into budget (budget_name)
	values ("savings"),("leisure"), ("shelter"), ("transport"), ("food"), ("credit"), ("goal"), ("salary"), ("investments"), ("other");
                
	insert into transactions (direction, budget_id, sub_category, trans_date, end_date, trans_amount, frequency, customer_id)
	values  ("in", 8, "main job", "2021-12-25", default, 2100, "month", 1),
            ("out", 5, "dinner friends", "2021-11-25", default, 45, "once", 1);
            
	insert into customer_budget(customer_id, budget_id, accurate_balance, user_def_balance)
    values (1, 8, default, default),
			(1, 5, default, default);
            
	insert into goal (goal_name, balance, target_amount, target_date, achieved, customer_id)
    values ("my goal", default, 500, "2022-10-01", default, '1');
            

SET SQL_SAFE_UPDATES = 1;