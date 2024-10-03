DELETE FROM Orders;
INSERT INTO Orders (id, client_id, product_id, quantity, orderDate, price, status) VALUES (1, 1, 1, 2, '2024-08-15T10:00:00', 15, null);
INSERT INTO Orders (id, client_id, product_id, quantity, orderDate, price, status) VALUES (2, 2, 2, 1, '2024-08-16T10:00:00', 25, null);

ALTER TABLE Orders ALTER COLUMN id RESTART WITH 3;