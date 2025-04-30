create table genres(
    id bigserial primary key,
    name varchar(32) not null unique
);