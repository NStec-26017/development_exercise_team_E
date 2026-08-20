// サンプルデータ挿入
INSERT INTO product_category (id , name) VALUES (10001 , '文具');
INSERT INTO product_category (id , name) VALUES (10002 , '雑貨');
INSERT INTO product_category (id , name) VALUES (10003 , 'パソコン周辺機器');

INSERT INTO product (id , product_category_id, name, price, image_url,) VALUES 
(11, 10001 , '黒鉛筆', 150, 'black_pen.jpg'),
(12, 10001 , '黒ボールペン', 150, 'black_pen_o.jpg'),
(13, 10001 , '赤ボールペン', 150, 'red_pen_o.jpg'),
(14, 10001 , '青ボールペン', 150, 'blue_pen_o.jpg'),
(15, 10001 , '青マーカー', 200, 'blue_maker.jpeg'),
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
