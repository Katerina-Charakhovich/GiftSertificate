CREATE SCHEMA IF NOT EXISTS ch_gift;
create table tag
(
    id_tag INTEGER AUTO_INCREMENT NOT NULL PRIMARY KEY,
    name_tag   varchar(50) not null,
    constraint tag_name_uindex unique (name_tag)
);

CREATE TABLE certificate (
  certificate_id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  certificate_name VARCHAR(45) NOT NULL,
  description VARCHAR(1000) NOT NULL,
  price DECIMAL NOT NULL,
  duration INT NOT NULL,
  create_date TIMESTAMP NOT NULL,
  last_update_date TIMESTAMP NOT NULL
 );

CREATE TABLE certificate_tag (
  certificate_id INT NOT NULL,
  id_tag INT NOT NULL,
  PRIMARY KEY (certificate_id, id_tag),
  FOREIGN KEY (certificate_id) REFERENCES certificate (certificate_id) ON DELETE CASCADE,
  FOREIGN KEY (id_tag) REFERENCES tag (id_tag) ON DELETE CASCADE);

CREATE TABLE user(
   user_id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
   user_name varchar(50) NOT NULL,
   user_surname varchar(50) NOT NULL
);
CREATE TABLE purchase (
  purchase_id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  user_id INT NOT NULL,
  purchase_price DECIMAL NOT NULL,
  create_date TIMESTAMP NOT NULL,
  last_update_date TIMESTAMP NOT NULL,
  FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE CASCADE);

create table purchase_certificate
(
    purchase_id    int not null,
    certificate_id int not null,
    primary key (purchase_id, certificate_id),
    constraint purchase_certificate_certificate_certificate_id_fk
        foreign key (certificate_id) references certificate (certificate_id),
    constraint purchase_certificate_purchase_purchase_id_fk
        foreign key (purchase_id) references purchase (purchase_id)
);
