create table TradingItem(
    id int,
    name nvarchar(100) not null,
    type nvarchar(100),
    price bigint
);

drop table TradingItem;