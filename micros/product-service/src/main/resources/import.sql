DELETE FROM Product;

INSERT INTO Product (id, name, description,price, available, quantity) VALUES (1, 'Product 1', 'Description for product 1', 10.0, true, 100);
INSERT INTO Product  (id, name, description,price, available, quantity) VALUES (2, 'Product 2', 'Description for product 2', 20.0, true, 500);
INSERT INTO Product  (id, name, description,price, available, quantity) VALUES (3, 'Product 3', 'Description for product 3', 30.0, true, 700);

ALTER TABLE Product ALTER COLUMN id RESTART WITH 4;
