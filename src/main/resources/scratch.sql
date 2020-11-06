create table TradingItem(
                            id int not null,
                            name nvarchar(100) not null,
                            type nvarchar(100),
                            price bigint,
                            creationDate date
);
drop table if exists TradingItem;

create table Orders(
                            id int not null,
                            tradingItemName nvarchar(100) not null,
                            type varchar(100),
                            price bigint,
                            creationDate date
);
drop table if exists Orders;

create table Users(
                            id int not null,
                            name nvarchar(100) not null,
                            surname nvarchar(100) not null,
                            cash bigint
);
drop table if exists Users;
