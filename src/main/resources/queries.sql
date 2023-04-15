//Create Customer Table
create table customer_details (
    id int not null AUTO_INCREMENT,
    customer_name varchar(50),
    mobile_no varchar(10),
    date_of_birth date,
    PRIMARY KEY (`id`));

insert into customer_details (id, customer_name, mobile_no, date_of_birth)
values (1000, 'James', '100000000', '2012-12-04');

insert into customer_details (id, customer_name, mobile_no, date_of_birth)
values (1002, 'Michael', '4356123457', '1994-05-23');

insert into customer_details (id, customer_name, mobile_no, date_of_birth)
values (1003, 'John', '7356123452', '1983-08-16');

alter table customer_details add column address_id int not null;

update customer_details set address_id = 1 where id = 1000;

//Queries for Address
create table address (
                         id int not null AUTO_INCREMENT,
                         street varchar(50),
                         city varchar(20),
                         state varchar(20),
                         zip_code varchar(20),
                         PRIMARY KEY (`id`));

insert into address (id, street, city, state, zip_code, customer_id)
values (1, 'Street 1', 'LA', 'California', '90001', 1000);

//Queries for Policy
create table policy_details (
                                id int not null AUTO_INCREMENT,
                                policy_no varchar(50),
                                coverage_amount int not null,
                                premium int not null,
                                effective_date date,
                                expiry_date date,
                                customer_id int not null,
                                PRIMARY KEY (`id`)
);

insert into policy_details (id, policy_no, coverage_amount, premium, effective_date, expiry_date, customer_id)
values (1, 'POL1', 100000, 500, '2023-01-20', '2023-01-20', 1000);