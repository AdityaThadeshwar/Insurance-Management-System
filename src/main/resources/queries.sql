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