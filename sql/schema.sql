//customerテーブル作成

create table customer (
    id SERIAL PRIMARY KEY ,
    name VARCHAR(20) ,
    name_kana VARCHAR(20) ,
    address1 varchar(100) ,
    address2 varchar(100) ,
    phone_number varchar(20) ,
    mail_address varchar(200) ,
    username varchar(30) ,
    password varchar(255) ,
    register_date timestamp 
)

//departmentテーブル作成
create table department(
	id SERIAL PRIMARY KEY ,
	name varchar(100) 
) ;

//employeeテーブル作成
create table employee(
	id SERIAL PRIMARY KEY ,
	department_id integer ,
	name varchar(100) ,
	name_kana varchar(100) ,

    FOREIGN KEY (department_id)
	    REFERENCES department(id)

)

//employee_accountテーブル作成
create table employee_account(
	id SERIAL PRIMARY KEY ,
	employee_id integer ,
	name varchar(20) ,
	password varchar(255) ,

	FOREIGN KEY (employee_id)
	    REFERENCES employee(id)
	
);

//product_categoryテーブル作成
create table product_category(
	id SERIAL PRIMARY KEY ,
	name varchar(30) 
) ;

//productテーブル作成
create table product(
	id SERIAL PRIMARY KEY ,
	product_category_id integer ,
	name varchar(20) ,
	price integer ,
	image_url varchar(200) ,
	delete_flag integer , 

	FOREIGN KEY (product_category_id)
	    REFERENCES product_category(id)
	
);

//product_stockテーブル作成.
create table product_stock(
	id SERIAL PRIMARY KEY ,
	product_id integer ,
	quantity integer , 

	FOREIGN KEY (product_id)
	    REFERENCES product(id)
	
);

//order_statusテーブル作成
create table order_status(
	id SERIAL PRIMARY KEY ,
	name varchar(100) 

)

//payment_methodテーブル作成
create table payment_method(
	id SERIAL PRIMARY KEY ,
	name varchar(100) 

)

//ordersテーブル作成
create table orders(
	id SERIAL PRIMARY KEY ,
	customer_id integer ,
	order_status_id integer ,
	payment_method_id integer ,
	order_date timestamp ,
	amount_total integer ,

	FOREIGN KEY (customer_id)
	    REFERENCES customer(id) ,
	 
	FOREIGN KEY (order_status_id)
	    REFERENCES order_status(id) ,
	 
	FOREIGN KEY (payment_method_id)
	    REFERENCES payment_method(id) 
) ;

//order_detailテーブル作成
create table order_detail(
	id SERIAL PRIMARY KEY ,
	order_id integer ,
	product_id integer ,
	unit_price integer ,
	count integer ,

	FOREIGN KEY (order_id)
	    REFERENCES orders(id) ,
	FOREIGN KEY (product_id)
	    REFERENCES product(id) 
) ;

insert into department (id,name) values (1001,'商品管理課');

insert into department (id,name) values (1002,'品質管理課');

insert into department (id,name) values (1003,'製品管理課');

insert into department (id,name) values (1004,'プロダクト管理課');

insert into department (id,name) values (1005,'人事課');

insert into employee (id,department_id,name,name_kana) values (101,1001,'フルネス太郎','フルネスタロウ');

insert into employee (id,department_id,name,name_kana) values (102,1002,'フルネス次郎','フルネスジロウ');

insert into employee (id,department_id,name,name_kana) values (103,1003,'フルネス三郎','フルネスサブロウ');

insert into employee (id,department_id,name,name_kana) values (104,1004,'豆田豆蔵','マメタマメゾウ');

insert into employee (id,department_id,name,name_kana) values (105,1005,'空田豆雄','ソラタマメオ');

insert into employee_account (id,employee_id,name,password) values (1,101,'fullness','$2a$12$KYP1pth4anLhFgsF2dCp4eD/DOhhpXflfE9FDsW5WGx6PNivOB00a');

insert into employee_account (id,employee_id,name,password) values (2,102,'NSTec','$2a$12$HkA9.GfY4onzhJCrhVF4jeDgQS/tGK4hWrGifAUjAerO0bI9SJJ4K');

insert into employee_account (id,employee_id,name,password) values (3,103,'NSTecfullness2026002','$2a$12$5y0.etr41KXCnu8qJXjXM.MEpK87Kt1ZIIPH9nmOqSvOwkIBN7a9G');
