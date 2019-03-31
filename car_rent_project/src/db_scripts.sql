/*car entity*/

CREATE TABLE car
(
  id     SERIAL PRIMARY KEY NOT NULL,
  brand  varchar(50)        NOT NULL,
  model  varchar(50)        NOT NULL,
  price  DOUBLE              NOT NULL,
  status varchar(50)        NOT NULL
);

INSERT INTO car (brand, model, price, status)
VALUES ('Ford', 'Focus', 50, 'working'),
  ('Ford', 'Fiesta', 35, 'damaged'),
  ('Opel', 'Astra', 45, 'working'),
  ('Volkswagen', 'Polo', 30, 'working'),
  ('Toyota', 'Yaris', 25, 'damaged');

select brand, model, price, status
from car;

/*user entity*/

CREATE TABLE users
(
  id       SERIAL PRIMARY KEY NOT NULL,
  name     VARCHAR(50)        NOT NULL,
  username VARCHAR(50)        NOT NULL,
  password VARCHAR(50)        NOT NULL,
  role     varchar(5)         NOT NULL
);

DROP TABLE user;

INSERT INTO users (name, username, password, role)
VALUES ('Eugene', 'Eugene123', 'Eugene123', 'User'),
  ('Alex', 'Alex321', 'Alex321', 'Admin');

/*order entity*/

CREATE TABLE orders
(
  id                 SERIAL PRIMARY KEY,
  passport       VARCHAR(256)       NOT NULL,
  created      DATE        NOT NULL,
  date_from          DATE        NOT NULL,
  date_to            DATE        NOT NULL,
  user_id            INT         NOT NULL,
  car_id             INT         NOT NULL,
  admin_id           INT,
  total_amount       DOUBLE         NOT NULL,
  status             VARCHAR(50) NOT NULL,
  reject_reason      VARCHAR(250),
  damage_description VARCHAR(250)


);
DROP TABLE orders;

alter table orders modify admin_id int null;

INSERT INTO orders (id,
                    created,
                    date_from,
                    date_to,
                    user_id,
                    car_id,
                    admin_id,
                    total_amount,
                    status,
                    reject_reason,
                    passport,
                    damage_description)
VALUES (123,
  SYSDATE(),
  SYSDATE(),
  SYSDATE(),
  1,
  1,
  1,
  123,
  'approved',
  null,
  '123',
        null);