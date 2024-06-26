-- SELECT User, Host FROM mysql.user;
-- drop database mathiasbank;
CREATE DATABASE IF NOT EXISTS mathiasbank
DEFAULT CHARACTER SET utf8
DEFAULT COLLATE utf8_general_ci;
use mathiasbank;
CREATE TABLE `User` (
    document INT PRIMARY KEY,
    email VARCHAR(255),
    full_name VARCHAR(255),
    address VARCHAR(255),
    user_type VARCHAR(30),
    password VARCHAR(255)
);

CREATE TABLE Client (
    document_cli INT PRIMARY KEY,
    role VARCHAR(30),
    active BOOLEAN,
    FOREIGN KEY (document_cli) REFERENCES User(document)
);

CREATE TABLE Employee (
    document_emp INT PRIMARY KEY,
    role VARCHAR(10),
    active BOOLEAN,
    FOREIGN KEY (document_emp) REFERENCES User(document)
);

CREATE TABLE Account (
    account_number INT PRIMARY KEY auto_increment,
    document_cli INT,
    active BOOLEAN,
    account_tipe VARCHAR(255),
    FOREIGN KEY (document_cli) REFERENCES Client(document_cli)
);

CREATE TABLE Card (
    card_id INT PRIMARY KEY auto_increment,
    card_number VARCHAR(16),
    account_number INT,
    issue_date DATETIME,
    expiration_date DATETIME,
    card_type VARCHAR(30),
    limit_amount DECIMAL(10, 2),
    available_balance DECIMAL(10, 2),
    closing_date DATETIME,
    FOREIGN KEY (account_number) REFERENCES Account(account_number)

);
-- agrego un aleatorio para nro de tarjeta
DELIMITER //
CREATE TRIGGER before_insert_card
BEFORE INSERT ON Card
FOR EACH ROW
BEGIN
    DECLARE chars VARCHAR(16);
    DECLARE i INT DEFAULT 1;
    DECLARE rnd INT;
    SET chars = '0123456789';
    SET NEW.card_number = '';
    WHILE i <= 16 DO
        SET rnd = FLOOR(1 + RAND() * 9);
        SET NEW.card_number = CONCAT(NEW.card_number, SUBSTRING(chars, rnd, 1));
        SET i = i + 1;
    END WHILE;
END;
//
DELIMITER ;
CREATE TABLE Task (
    date timestamp,
    employee_document INT,
    description VARCHAR(255),
    FOREIGN KEY (employee_document) REFERENCES Employee(document_emp)
);

CREATE TABLE `Transaction` (
    transaction_number INT PRIMARY KEY auto_increment,
    account_number INT,
    client_document INT,
    card_id INT,
    date_trans DATETIME,
    amount DECIMAL(10, 2),
    alert VARCHAR(255),
    destination_account INT,
    description VARCHAR(255),
    currency INT,
    transaction_type VARCHAR(255),
    ordinal smallint default 1,
    processed boolean default false,
    FOREIGN KEY (account_number) REFERENCES Account(account_number),
    FOREIGN KEY (client_document) REFERENCES Client(document_cli),
    FOREIGN KEY (card_id) REFERENCES Card(card_id)
);

CREATE TABLE Balance (
    account_number INT primary key,
    amount_pesos DECIMAL(10, 2),
    amount_dollars DECIMAL(10, 2),
    overdraft_limit DECIMAL(10, 2),
    FOREIGN KEY (account_number) REFERENCES Account(account_number)
);

select * from user;


select * from Client;
-- insert into client values (51114096, "Premium",true);

select * from account;
select * from card;