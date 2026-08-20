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
	unit_price integer ,
	count integer ,

	FOREIGN KEY (order_id)
	    REFERENCES orders(id) ,
	FOREIGN KEY (product_id)
	    REFERENCES product(id)
) ;

// サンプルデータ挿入
INSERT INTO product_category (id , name) VALUES (10001 , '文具');
INSERT INTO product_category (id , name) VALUES (10002 , '雑貨');
INSERT INTO product_category (id , name) VALUES (10003 , 'パソコン周辺機器');

INSERT INTO product (id , product_category_id, name, price, image_url,) VALUES 
(11, 10001 , '黒鉛筆', 150, 'black_pen.jpg'),
(12, 10001 , '黒ボールペン', 150, 'black_pen_o.jpg'),
(13, 10001 , '赤ボールペン', 150, 'red_pen_o.jpg'),
(14, 10001 , '青ボールペン', 150, 'blue_pen_o.jpg'),
(15, 10001 , '青マーカー', 200, 'blue_maker.jpg'),
(16, 10001 , '赤マーカー', 200, 'red_maker.jpg'),
(17, 10001 , '緑マーカー', 200, 'green_maker.jpg'),
(18, 10001 ,  '黄マーカー', 200, 'yellow_maker.jpg'),
(19, 10001 , '筆ペン', 250, 'black_fudepen.jpg'),
(20, 10001 , '色鉛筆12色', 500, 'color_pen12.jpeg'),
(21, 10001 , '色鉛筆48色', 1500, 'color_pen48.jpeg'),
(22, 10001 , 'blackPEN', 300, 'black_pen_w.jpg'),
(23, 10001 , 'redPEN', 300, 'red_pen_w.jpg'),
(24, 10001 , 'bluePEN', 300, 'blue_pen_w.jpg'),
(25, 10002 , '防水スプレー', 500, 'spray.jpg'),
(26, 10002 , '傘', 650, 'umbrella.jpg'),
(27, 10002 , 'バッグ', 1000, 'bag.jpg'),
(28, 10002 , 'アイマスク', 300, 'mask.jpg'),
(29, 10002 , 'ハンカチ', 200, 'cloth.jpg'),
(30, 10002 , 'キーリング', 100, 'keyholder.jpg'),
(31, 10002 ,'キーホルダー', 100, 'keyholder2.jpg');
drop sequence if exists employee_account ;
drop sequence if exists employee ;
drop sequence if exists order_detail ;
drop sequence if exists product_stock ;
drop sequence if exists product ;
drop sequence if exists orders ;
drop sequence if exists order_status ;
drop sequence if exists payment_method ;
drop sequence if exists product_category ;
drop sequence if exists customer ;
