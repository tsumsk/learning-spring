create table if not exists Ingredient (
    id varchar(4) not null,
    name varchar(25) not null,
    type varchar(10) not null,
    primary key (id)
);

create table if not exists Taco (
    id bigint auto_increment,
    name varchar(50) not null,
    created_at timestamp not null,
    primary key (id)
);

create table if not exists Taco_Ingredients (
    taco_id bigint not null,
    ingredient_id varchar(4) not null
);

create table if not exists Taco_Order (
    id bigint auto_increment,
    name varchar(50) not null,
    street varchar(50) not null,
    city varchar(50) not null,
    state varchar(2) not null,
    zip varchar(10) not null,
    cc_number varchar(16) not null,
    cc_expiration varchar(5) not null,
    cc_cvv varchar(3) not null,
    created_at timestamp not null,
    primary key (id)
);

create table if not exists Taco_Order_Tacos (
    taco_order_id bigint not null,
    taco_id bigint not null
);

create table if not exists Taco_User (
    id bigint auto_increment,
    username varchar(50) not null,
    password varchar(256) not null,
    enabled smallint not null,
    street varchar(50) not null default '',
    city varchar(50) not null default '',
    state varchar(2) not null default '',
    zip varchar(10) not null default '',
    phone varchar(11) not null default '',
    primary key (id)
);

create table if not exists Taco_User_Orders (
    taco_user_id bigint not null,
    taco_order_id bigint not null
);

create table if not exists Taco_User_Authorities (
    id bigint auto_increment,
    username varchar(50) not null,
    authority varchar(50) not null,
    primary key (id)
);
