use my_budgeter;

insert into customer (first_name, email, bal_name, current_bal)
	values ("Joe", "j@mail.com", "", 1200);


insert into transactions (direction, category, sub_category, trans_date, end_date, trans_amount, frequency, customer_id)
	values  ("in", "investments", "annual interest", "2021-12-15 00:00:00", default, 125.34, "once", 1),
            ("in", "salary", "main job", "2021-11-25 00:00:00", default, 1200, "month", 1),
            ("in", "salary", "second job", "2021-11-14 00:00:00", default, 210.00, "week", 1),
            ("in", "investments", "annual interest", "2021-12-15 00:00:00", default, 125.34, "once", 1),
            ("out", "transport", "commute", default, default, 200.00, "month", 1),
            ("out", "food", "groceries", default, default, 65.00, "week", 1),
            ("out", "food", "takeaways", default, default, 70.00, "month", 1),
            ("out", "shelter", "mortgage", "2021-11-01 00:00:00", "2046-06-01 00:00:00", 550.00, "month", 1),
            ("out", "shelter", "gas", "2021-11-24 00:00:00", default, 34.80, "month", 1),
            ("out", "shelter", "electricity","2021-11-08 00:00:00", default, 40.00, "month", 1),
            ("out", "shelter", "water", "2021-12-05 00:00:00", default, 168.00, "half", 1),
            ("out", "shelter", "council tax", "2021-11-28 00:00:00", default, 110.00, "month", 1),
            ("out", "savings", "", "2021-11-25 00:00:00", default, 200.00, "month", 1),
            ("out", "goal", "", "2021-11-25 00:00:00", default, 100.00, "month", 1);
                        
-- "savings", "leisure", "shelter", "transport", "food", "credit", "buffer", "goal", "salary","investments","other"

insert into goal (`name`, target_amount, target_date, customer_id)
	values ("office renovation", 350.00, "2022-01-15 00:00:00", 1),
			("new car", 4500.00, "2023-01-15 00:00:00", 1); 