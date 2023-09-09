create table users(

    id bigint not null auto_increment,
    login varchar(200) not null unique,
    password varchar(200) not null,

    primary key(id)
);