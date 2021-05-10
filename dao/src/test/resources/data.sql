INSERT INTO tag (id_tag, name_tag) VALUES
(1, 'test_sport'),
(2, 'test_spa'),
(3, 'test_relax');

INSERT INTO certificate (certificate_id, certificate_name,
description, price, duration, create_date, last_update_date) VALUES
(1, 'certificate_name1', 'certificate_desc1', 10, 10, '2021-03-31 12:15:37', '2021-03-31 12:15:37'),
(2, 'certificate_name2', 'certificate_desc2', 10, 10, '2021-03-31 12:15:37', '2021-03-31 12:15:37'),
(3, 'certificate_name3', 'certificate_desc3', 10, 10, '2021-03-31 12:15:37', '2021-03-31 12:15:37'),
(4, 'certificate_name4', 'certificate_desc4', 10, 10, '2021-03-31 12:15:37', '2021-03-31 12:15:37');
INSERT INTO certificate_tag (certificate_id, id_tag) VALUES
(1, 1),
(1, 2),
(1, 3),
(2, 2),
(2, 3),
(3, 1);
INSERT INTO user (user_id, user_name,user_surname) VALUES
(1, 'Михаил', 'Черехович',misha,12345),
(2, 'Виктрор', 'Высоцкий',victor,123456),
(3, 'Виталий', 'Шахлевич',vital,123456);
INSERT INTO purchase (purchase_id, user_id, purchase_price, create_date, last_update_date) VALUES
(1, 1, 120.00, '2021-04-23 11:19:30', '2021-04-23 11:19:30'),
(2, 2, 100.00, '2021-04-23 11:19:30', '2021-04-23 11:19:30'),
(7, 2, 10.15, '2021-04-25 21:04:16', '2021-04-25 21:04:16'),
(8, 2, 10.20, '2021-04-25 21:18:51', '2021-04-25 21:18:51'),
(9, 2, 10.20, '2021-04-25 21:24:57', '2021-04-25 21:24:58'),
(10, 1, 10.15, '2021-04-25 21:38:29', '2021-04-25 21:38:30'),
(11, 1, 10.15, '2021-04-25 21:38:47', '2021-04-25 21:38:48'),
(12, 1, 10.15, '2021-04-25 21:40:41', '2021-04-25 21:40:41'),
(13, 1, 10.15, '2021-04-25 21:41:31', '2021-04-25 21:41:31');
INSERT INTO purchase_certificate(purchase_id, certificate_id) VALUES
(1, 3),
(7, 3),
(8, 3),
(9, 3),
(10, 3),
(11, 3),
(12, 3),
(13, 3),
(8, 4),
(9, 4);



