create table authors (
   id bigserial primary key,
   name varchar(64) not null unique
);