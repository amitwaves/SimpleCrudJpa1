insert into EMPLOYER (name, last_updated_timestamp, total_cost) 
values ('Microsoft', CURRENT_TIMESTAMP, 45000.00);

insert into EMPLOYER (name, last_updated_timestamp, total_cost) 
values ('Tesla', CURRENT_TIMESTAMP, 100000.00);

insert into PROJECT (name, start_date, end_date, last_updated_timestamp, department_number, cost, employer_id) 
values ('Project 1', '2019-01-01', '2019-01-05', CURRENT_TIMESTAMP, 101, 25000.00, (select id from EMPLOYER where name='Microsoft'));

insert into PROJECT (name, start_date, end_date, last_updated_timestamp, department_number, cost, employer_id) 
values ('Project 2', '2019-01-10', '2019-01-15', CURRENT_TIMESTAMP, 102, 20000.00, (select id from EMPLOYER where name='Microsoft'));

insert into PROJECT (name, start_date, end_date, last_updated_timestamp, department_number, cost, employer_id) 
values ('Project 1', '2019-01-31', '2019-02-15', CURRENT_TIMESTAMP, 201, 100000.00, (select id from EMPLOYER where name='Tesla'));