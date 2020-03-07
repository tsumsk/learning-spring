create table if not exists Ingredient (
    id varchar(4) not null,
    name varchar(25) not null,
    type varchar(10) not null,
    primary key (id)
);

create table if not exists Taco (
    id bigint auto_increment,
    name varchar(50) not null,
    createdAt timestamp not null,
    primary key (id)
);

create table if not exists Taco_Ingredients (
    tacoId bigint not null,
    ingredientId varchar(4) not null
);

create table if not exists Taco_Order (
    id bigint auto_increment,
    name varchar(50) not null,
    street varchar(50) not null,
    city varchar(50) not null,
    state varchar(2) not null,
    zip varchar(10) not null,
    ccNumber varchar(16) not null,
    ccExpiration varchar(5) not null,
    ccCVV varchar(3) not null,
    createdAt timestamp not null,
    primary key (id)
);

create table if not exists Taco_Order_Tacos (
    tacoOrderId bigint not null,
    tacoId bigint not null
);
