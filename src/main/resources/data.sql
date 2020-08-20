INSERT INTO CUSTOMER (name, surname, email) VALUES ('Ivan', 'Ivanov', 'ivan@ivan.com');
INSERT INTO CUSTOMER (name, surname, email) VALUES ('Petr', 'Petrov', 'petr@petr.com');

INSERT INTO RESERVATION (reservation_date) VALUES ('2020-01-01');
INSERT INTO RESERVATION (reservation_date) VALUES ('2020-05-05');

INSERT INTO CUSTOMER_RESERVATION (fk_customer, fk_reservation) VALUES (1, 1);
INSERT INTO CUSTOMER_RESERVATION (fk_customer, fk_reservation) VALUES (1, 2);
INSERT INTO CUSTOMER_RESERVATION (fk_customer, fk_reservation) VALUES (2, 2);