INSERT INTO `user`(username, email, password) VALUE ('adicz', 'adicz@shop.pl', '$2a$12$u329jke.zRABSiNaBhW8RelHL7ULjjfJE1vZp2NBe1qRoYB9Sky42');  /*12345*/
INSERT INTO `user`(username, email, password) VALUE ('admin', 'admin@shop.pl', '$2a$12$u329jke.zRABSiNaBhW8RelHL7ULjjfJE1vZp2NBe1qRoYB9Sky42');  /*12345*/
INSERT INTO `user`(username, email, password) VALUE ('adam', 'adam@shop.pl', '$2a$12$u329jke.zRABSiNaBhW8RelHL7ULjjfJE1vZp2NBe1qRoYB9Sky42');  /*12345*/

INSERT INTO `role` (name) values ('ADMIN');
INSERT INTO `role` (name) values ('USER');
INSERT INTO `role` (name) values ('MODERATOR');

/*
 adicz -> USER, MODERATOR
 admin -> ADMIN, USER, MODERATOR
 adam -> USER
 */
INSERT INTO `user_roles`(user_id, roles_id) VALUE (1, 2);
INSERT INTO `user_roles`(user_id, roles_id) VALUE (1, 3);
INSERT INTO `user_roles`(user_id, roles_id) VALUE (2, 1);
INSERT INTO `user_roles`(user_id, roles_id) VALUE (2, 2);
INSERT INTO `user_roles`(user_id, roles_id) VALUE (2, 3);
INSERT INTO `user_roles`(user_id, roles_id) VALUE (3, 2);

INSERT INTO `category`(id, name, parent_id) VALUE (1, 'Elektronika', null);
INSERT INTO `category`(id, name, parent_id) VALUE (2, 'Telefony i Akcesoria', 1);
INSERT INTO `category`(id, name, parent_id) VALUE (3, 'Komputery', 1);
INSERT INTO `category`(id, name, parent_id) VALUE (4, 'Konsole i automaty', 1);
INSERT INTO `category`(id, name, parent_id) VALUE (5, 'RTV i AGD', 1);
INSERT INTO `category`(id, name, parent_id) VALUE (6, 'Fotografia', 1);
INSERT INTO `category`(id, name, parent_id) VALUE (7, 'Akcesoria GSM', 2);
INSERT INTO `category`(id, name, parent_id) VALUE (8, 'Karty pamięci', 2);
INSERT INTO `category`(id, name, parent_id) VALUE (9, 'Powerbanki', 2);
INSERT INTO `category`(id, name, parent_id) VALUE (10, 'Akcesoria (Laptop, PC)', 3);
INSERT INTO `category`(id, name, parent_id) VALUE (11, 'Części do laptopów', 3);
INSERT INTO `category`(id, name, parent_id) VALUE (12, 'Drukarki i skanery', 3);
INSERT INTO `category`(id, name, parent_id) VALUE (13, 'Dyski i pamięci przenośne', 3);

INSERT INTO `product`(title, availability, category_id, price, brand, version) VALUE ('Drukarka wielofunkcyjna atramentowa (kolor) HP 2720e', 10, 12, 289, 'HP', 1);
INSERT INTO `product`(title, availability, category_id, price, brand, version) VALUE ('Drukarka wielofunkcyjna atramentowa (kolor) Canon TS3350', 14, 12, 279, 'Canon', 1);
INSERT INTO `product`(title, availability, category_id, price, brand, version) VALUE ('Drukarka 3D Creality Ender-3 V2', 3, 12, 1035.00, 'Creality', 1);
INSERT INTO `product`(title, availability, category_id, price, brand, version) VALUE ('Drukarka jednofunkcyjna atramentowa (kolor) HP OfficeJet 200', 45, 12, 399, 'HP', 1);
INSERT INTO `product`(title, availability, category_id, price, brand, version) VALUE ('Drukarka wielofunkcyjna laserowa (mono) Brother DCP-1512E', 1, 12, 797, 'Brother', 1);
INSERT INTO `product`(title, availability, category_id, price, brand, version) VALUE ('UCHWYT NA NAWIGACJĘ TELEFON SZYBĘ DESKĘ KOKPIT GPS', 1, 7, 32, 'Noname', 1);
INSERT INTO `product`(title, availability, category_id, price, brand, version) VALUE ('TRANSMITER FM Bluetooth + Zestaw Głośnomówiący', 7, 7, 49, 'Noname', 1);
INSERT INTO `product`(title, availability, category_id, price, brand, version) VALUE ('BASEUS POWER BANK USB-C DO LAPTOPA 65W - 20000mAh', 2, 9, 249.50, 'Baseus', 1);
INSERT INTO `product`(title, availability, category_id, price, brand, version) VALUE ('SSD GOODRAM CX400 256GB SATA III 2,5 RETAIL', 100, 13, 129.69, 'GOODRAM', 1);
INSERT INTO `product`(title, availability, category_id, price, brand, version) VALUE ('PAMIĘĆ RAM 8GB PC3L DDR3L SO-DIMM 12800S 1600MHz', 23, 11, 98, 'Noname', 1);
INSERT INTO `product`(title, availability, category_id, price, brand, version) VALUE ('Kabel HDMI Jsaux CV0035-3M 3 m czarny', 50, 10, 40, 'Jsaux', 1);

INSERT INTO `address` (id, country, city, house_number, postal_code, street) VALUES (1, 'Poland', 'Warszawa', '6A', '00 - 902', 'Wiejska');
INSERT INTO `product_count` (id, count, product_id) VALUES (1, 1, 1);
INSERT INTO `product_count` (id, count, product_id) VALUES (2, 2, 2);
INSERT INTO `shopping_cart` (id, total_price) VALUES (1, 289);
INSERT INTO `shopping_cart` (id, total_price) VALUES (2, 578);
INSERT INTO `shopping_cart_product_counts` (shopping_cart_id, product_counts_id) VALUES (1, 1);
INSERT INTO `shopping_cart_product_counts` (shopping_cart_id, product_counts_id) VALUES (2, 2);

INSERT INTO `shipping_method` (id, name, price) VALUES (1, 'DHL', 20);
INSERT INTO `shipping_method` (id, name, price) VALUES (2, 'INPOST_S', 15);
INSERT INTO `shipping_method` (id, name, price) VALUES (3, 'INPOST_M', 18);
INSERT INTO `shipping_method` (id, name, price) VALUES (4, 'INPOST_L', 21);
INSERT INTO `shipping_method` (id, name, price) VALUES (5, 'PERSONAL_COLLECT', 0);

INSERT INTO `order` (id, order_date,shipping_method_id, order_status, payment_method, address_id, shopping_cart_id, user_id, price) VALUES (1, CURRENT_TIMESTAMP, 1, 'READY', 'BLIK', 1, 1, 1, 309);