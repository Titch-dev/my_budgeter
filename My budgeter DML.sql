use my_budgeter;

select * 
from transactions;

select * 
from goal;

select *
from customer;

select sum(trans_amount)
from transactions
where trans_date < current_timestamp;

select sum(trans_amount)
from transactions
where direction = "in";

-- current incoming balance
-- monthly transactions
update customer 
set current_in = current_in + (
					select sum(trans_amount)
					from transactions
					where direction = "in" && frequency = "month"
                )
where id = 1;

-- weekly transactions
update customer 
set current_in = current_in + (
								select sum(trans_amount)
								from transactions
								where direction = "in" && frequency = "week"
							) * 4
where id = 1;

-- current outgoing balance
-- monthly transactions
update customer 
set current_out = current_out + (
								select sum(trans_amount)
								from transactions
								where direction = "out" && frequency = "month"
								)
where id = 1;

-- half annual
update customer 
set current_out = current_out + (
								select sum(trans_amount)
								from transactions
								where direction = "out" && frequency = "half"
								) / 6
where id = 1;


-- weekly
update customer
set current_out = current_out + (
								select sum(trans_amount)
                                from transactions
                                where direction = "out" && frequency = "week"
                                ) * 4
where id = 1;


-- update current balance

update customer
set current_bal = current_in - current_out
where id = 1;

-- update goal balance
update customer
set bal_goal = bal_goal + (
							select sum(trans_amount)
							from transactions
							where category = "goal" && direction = "out"
						   )
where id = 1;

-- update savings balance
update customer
set bal_savings = bal_savings + (
								select sum(trans_amount)
								from transactions
								where category = "savings" && direction = "out"
								)
where id = 1;

select * from customer;
