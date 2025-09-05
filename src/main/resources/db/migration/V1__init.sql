Create Table Product(
 id Serial Primary key,
 name varchar(255) not null,
 Sku varchar(100) unique not null,
 price NUMERIC (10,2) not null,
 stock INT not null
);