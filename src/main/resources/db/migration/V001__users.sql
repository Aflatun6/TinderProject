-- 2020.05.14
create table users
(
    id serial not null
        constraint users_pk
            primary key,
    name varchar,
    imageurl varchar ,
    password varchar default 123
);
alter table users
    owner to slwfzdunpgpvgz;

create unique index users_name_uindex
    on users (name);
