create table user
(
    id          int                                   primary key auto_increment,
    first_name  varchar(63)                           not null,
    last_name   varchar(63)                           not null,
    email       varchar(63)                           not null,
    password    varchar(31)                           not null,
    birthday    DATE                                  not null
);