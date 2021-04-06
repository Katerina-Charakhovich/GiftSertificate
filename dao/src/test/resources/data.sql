INSERT INTO tag (id_tag, name_tag) VALUES
(1, 'sport'),
(2, 'spa'),
(3, 'relax');

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
