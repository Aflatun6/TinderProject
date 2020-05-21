create table messages
(
    id      serial                  not null
        constraint messages_pk
            primary key,
    who     integer                 not null,
    whom    integer                 not null,
    content varchar                 not null,
    date    timestamp default now() not null
);

alter table messages
    owner to slwfzdunpgpvgz;

create unique index messages_id_uindex
    on messages (id);
