create table messages
(
    who     integer                 not null,
    whom    integer                 not null,
    content varchar                 not null,
    date    timestamp default now() not null
);

alter table messages
    owner to slwfzdunpgpvgz;
