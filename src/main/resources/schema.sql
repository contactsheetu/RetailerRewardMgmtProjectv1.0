DROP TABLE IF EXISTS TRANSACTION;
DROP TABLE IF EXISTS USER;

CREATE TABLE USER
(ID INT PRIMARY KEY, 
 FIRSTNAME VARCHAR,
 LASTNAME VARCHAR
 );
 
 
CREATE TABLE TRANSACTION
(TRANSACTIONID INT PRIMARY KEY AUTO_INCREMENT, 
 AMOUNT NUMERIC(20, 2),
 TRANSACTIONDATE DATETIME,
 DESCRIPTION VARCHAR,
 USER_ID VARCHAR
 );
 
alter table TRANSACTION add constraint TRANSACTION_USER_ID foreign key (USER_ID) references USER(id);