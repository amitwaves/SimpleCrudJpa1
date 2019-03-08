create sequence if not exists employer_id_seq
start with 1 increment by 1 cache 100;

create table if not exists EMPLOYER (
id bigint auto_increment primary key,
version bigint not null default 1,
name varchar(255) not null,
last_updated_timestamp timestamp with time zone not null,
total_cost number(20,2) default 0.0 not null,
deleted boolean default false
);

create sequence if not exists project_id_seq
start with 1 increment by 1 cache 100;

create table if not exists PROJECT (
id bigint auto_increment primary key,
version bigint not null default 1,
name varchar(255) not null,
start_date date not null,
end_date date not null,
last_updated_timestamp timestamp with time zone,
department_number bigint not null,
cost number(12,2) default 0.0 not null,
employer_id bigint,
deleted boolean default false,
);

