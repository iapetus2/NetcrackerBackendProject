create table TradingItem(
                            id int primary key not null,
                            name nvarchar(100) not null,
                            type nvarchar(100),
                            price bigint,
                            creationDate date
);

create table Orders(
                            id int primary key not null,
                            tradingItemName nvarchar(100) not null,
                            type varchar(5),
                            price bigint,
                            creationDate date
);


create table Users(
                            id int primary key not null,
                            name nvarchar(100) not null,
                            surname nvarchar(100) not null,
                            cash bigint
);

create table Deals(
                            id int primary key not null,
                            tradingItemName nvarchar(100) not null,
                            price bigint,
                            dealDate date
);

