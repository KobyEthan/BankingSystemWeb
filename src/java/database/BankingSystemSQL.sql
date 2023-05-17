DROP SEQUENCE customer_seq;
DROP TABLE Asset;
DROP TABLE Liability;
DROP TABLE Transaction1;
DROP TABLE Account;
DROP TABLE Customer;
DROP TABLE User_Role;

CREATE TABLE User_Role (
Role_Type VARCHAR(20),
All_Users_Power BOOLEAN,
View_Accounts BOOLEAN,
Edit_Accounts BOOLEAN,
Create_Accounts BOOLEAN,
View_Transactions BOOLEAN,
Transfer_Funds BOOLEAN,
View_Customers BOOLEAN,
Edit_Customers BOOLEAN,
Create_Customers BOOLEAN,
Create_User_Roles BOOLEAN,
PRIMARY KEY(Role_Type)
);

INSERT INTO User_Role (Role_Type, All_Users_Power, View_Accounts, Edit_Accounts, Create_Accounts, View_Transactions, Transfer_Funds, View_Customers, Edit_Customers, Create_Customers, Create_User_Roles)
VALUES
('User', FALSE, TRUE, TRUE, TRUE, TRUE, TRUE, FALSE, FALSE, FALSE, FALSE),
('Management', TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, FALSE),
('Administrator', TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE);


CREATE TABLE Customer(
Role_Type Varchar(20) NOT NULL,
Customer_Id VARCHAR(30),
First_Name VARCHAR(30),
Last_Name VARCHAR(30),
Phone_Number VARCHAR(11),
User_Id VARCHAR(20),
Password VARCHAR(20),
PRIMARY KEY(Customer_Id),
FOREIGN KEY(Role_Type) REFERENCES User_Role(Role_Type) ON DELETE CASCADE
);

INSERT INTO Customer (Role_Type, Customer_Id, First_Name, Last_Name, Phone_Number, User_Id, Password)
VALUES
('User','101','Customer','1', '1111111111', 'Cust1','cust1'),
('User','102','Customer','2', '2222222222', 'Cust2','cust2'),
('User','103','Customer','3', '3333333333', 'Cust3', 'cust3'),
('Management', '104', 'Manager', '1', '4444444444', 'Mgt1', 'mgt1'),
('Administrator', '105', 'Administrator', '1', '5555555555', 'Admin', 'admin');


CREATE TABLE Account (
Account_Number VARCHAR(10),
Account_Type VARCHAR(20),
Customer_Id VARCHAR(30),
Account_Name VARCHAR(30),
Date_Opened VARCHAR(20),
Is_Asset BOOLEAN,
PRIMARY KEY(Account_Number),
FOREIGN KEY(Customer_Id) REFERENCES Customer(Customer_Id) ON DELETE CASCADE
);

INSERT INTO Account (Account_Number, Account_Type, Customer_Id, Account_Name, Date_Opened, Is_Asset)
VALUES
('DJF283', 'Asset', '101', 'Asset', '2021-07-09', TRUE),
('IOJ475', 'Liability', '101', 'Liability', '2021-08-09', FALSE),
('AXW324', 'Asset', '102', 'Asset', '2020-04-20', TRUE),
('GHT409', 'Liability', '102', 'Liability', '2020-05-04', FALSE),
('JFK743', 'Asset', '103', 'Asset', '2020-07-20', TRUE),
('SRT576', 'Liability', '103', 'Liability', '2020-08-20', FALSE),
('YTR298', 'Asset', '104', 'Asset', '2004-07-22', TRUE),
('OPL371', 'Liability', '104', 'Liability', '2010-03-12', FALSE),
('AES486', 'Asset', '105', 'Asset', '2015-06-28', TRUE),
('REW970', 'Liability', '105', 'Liability', '2018-02-14', FALSE);

CREATE TABLE Asset (
Account_Number VARCHAR(10),
Interest_Rate DOUBLE,
Transaction_Fee DOUBLE,
FOREIGN KEY (Account_Number) REFERENCES Account(Account_Number) ON DELETE CASCADE
);

INSERT INTO Asset (Account_Number, Interest_Rate, Transaction_Fee)
VALUES
('DJF283', 0.08, 50.0),
('AXW324', 0.075, 45.0),
('JFK743', 0.07, 45),
('YTR298', 0.06, 25),
('AES486', 0.1, 50);

CREATE TABLE Liability (
Account_Number VARCHAR(10),
Interest_Rate DOUBLE,
Monthly_Payment DOUBLE,
Payment_Date VARCHAR(20),
FOREIGN KEY (Account_Number) REFERENCES Account(Account_Number) ON DELETE CASCADE
);

INSERT INTO Liability (Account_Number, Interest_Rate, Monthly_Payment, Payment_Date)
VALUES
('IOJ475', 0.03, 300.0, '2022-08-09'),
('GHT409', 0.09, 150, '2021-05-04'),
('SRT576', 0.08, 300, '2022-08-20'),
('OPL371', 0.09, 250, '2012-03-12'),
('REW970', 0.06, 190, '2020-02-14');

CREATE TABLE Transaction1 (
Transaction_Id VARCHAR(30),
Transaction_Amount DECIMAL(8,2),
Description VARCHAR(30),
Transaction_Date VARCHAR(20),
Account_Number VARCHAR(10),
Transaction_Type VARCHAR(20),
PRIMARY KEY (Transaction_Id),
FOREIGN KEY(Account_Number) REFERENCES Account(Account_Number) ON DELETE CASCADE
);

INSERT INTO Transaction1
(Transaction_Id, Transaction_Amount, Description, Transaction_Date, Account_Number, Transaction_Type)
VALUES
    ('100', 2000.00, 'Description', '2020-03-04', 'DJF283', 'debit'),
    ('101', 1000.00, 'Description', '2020-03-05', 'DJF283', 'debit'),
    ('102', 3000.00, 'Description', '2020-04-05', 'DJF283', 'debit'),

    ('103', 500.00, 'Description', '2020-03-15', 'IOJ475', 'credit'),
    ('104', 800.00, 'Description', '2020-03-20', 'IOJ475', 'credit'),
    ('105', 100.00, 'Description', '2020-03-28', 'IOJ475', 'credit'),

    ('106', 5000.00, 'Description', '2021-07-02', 'AXW324', 'debit'),
    ('107', 700.00, 'Description', '2021-07-28', 'AXW324', 'debit'),
    ('108', 2000.00, 'Description', '2021-09-05', 'AXW324', 'debit'),

    ('109', 450.00, 'Description', '2021-10-06', 'GHT409', 'credit'),
    ('110', 1000.00, 'Description', '2021-10-30', 'GHT409', 'credit'),
    ('111', 900.00, 'Description', '2021-12-25', 'GHT409', 'credit'),

    ('112', 200.00, 'Description', '2020-12-25', 'JFK743', 'debit'),
    ('113', 200.00, 'Description', '2021-01-25', 'JFK743', 'debit'),
    ('114', 200.00, 'Description', '2021-02-25', 'JFK743', 'debit'),

    ('115', 90.00, 'Description', '2021-01-26', 'SRT576', 'credit'),
    ('116', 90.00, 'Description', '2021-02-27', 'SRT576', 'credit'),
    ('117', 90.00, 'Description', '2021-03-25', 'SRT576', 'credit'),

    ('118', 5000.00, 'Description', '2021-07-02', 'YTR298', 'debit'),
    ('119', 700.00, 'Description', '2021-07-28', 'YTR298', 'debit'),
    ('120', 2000.00, 'Description', '2021-09-05', 'YTR298', 'debit'),

    ('121', 500.00, 'Description', '2020-03-15', 'OPL371', 'credit'),
    ('122', 800.00, 'Description', '2020-03-20', 'OPL371', 'credit'),
    ('123', 100.00, 'Description', '2020-03-28', 'OPL371', 'credit'),

    ('124', 2000.00, 'Description', '2020-03-04', 'AES486', 'debit'),
    ('125', 1000.00, 'Description', '2020-03-05', 'AES486', 'debit'),
    ('126', 3000.00, 'Description', '2020-04-05', 'AES486', 'debit'),

    ('127', 450.00, 'Description', '2021-10-06', 'REW970', 'credit'),
    ('128', 1000.00, 'Description', '2021-10-30', 'REW970', 'credit'),
    ('129', 900.00, 'Description', '2021-12-25', 'REW970', 'credit');

CREATE SEQUENCE customer_seq
START WITH 105
INCREMENT BY 1
MINVALUE 1
MAXVALUE 99999;

