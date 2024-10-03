CREATE TABLE Account (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         account_id VARCHAR(255) NOT NULL,
                         client_id INTEGER NOT NULL,
                         client_name VARCHAR(255) NOT NULL,
                         client_email VARCHAR(255) NOT NULL,
                         balance DOUBLE NOT NULL
);