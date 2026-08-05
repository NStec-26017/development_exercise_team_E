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
    password varchar(300) ,
    register_date timestamp 
)

//departmentテーブル作成
create table department(
	id SERIAL PRIMARY KEY ,
	name varchar(50) 
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
	password varchar(20) ,

	FOREIGN KEY (employee_id)
	    REFERENCES employee(id)
	
);

//product_categoryテーブル作成
create table product_category(
	id SERIAL PRIMARY KEY ,
	name varchar(20) 
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

//product_stockテーブル作成
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
	customer_id integer ,
	count integer ,

	FOREIGN KEY (order_id)
	    REFERENCES orders(id) ,
	FOREIGN KEY (product_id)
	    REFERENCES product(id) ,
	FOREIGN KEY (customer_id)
	    REFERENCES customer(id)


) ;
