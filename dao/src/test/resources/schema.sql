CREATE TABLE  IF NOT EXISTS tag (
  id_tag INT NOT NULL AUTO_INCREMENT,
  name_tag VARCHAR(45) NOT NULL,
  PRIMARY KEY (id_tag));

CREATE TABLE certificate (
  certificate_id INT NOT NULL AUTO_INCREMENT,
  certificate_name VARCHAR(45) NOT NULL,
  description VARCHAR(1000) NOT NULL,
  price DECIMAL NOT NULL,
  duration INT NOT NULL,
  create_date TIMESTAMP NOT NULL,
  last_update_date TIMESTAMP NOT NULL,
  PRIMARY KEY (certificate_id));

CREATE TABLE certificate_tag (
  certificate_id INT NOT NULL,
  id_tag INT NOT NULL,
  PRIMARY KEY (certificate_id, id_tag),
  FOREIGN KEY (certificate_id) REFERENCES certificate (certificate_id) ON DELETE CASCADE,
  FOREIGN KEY (id_tag) REFERENCES tag (id_tag) ON DELETE CASCADE);
