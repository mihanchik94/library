create table books(
    id bigserial primary key,
    title varchar(128) not null,
    author_id bigint references authors(id)
);

create extension if not exists pg_trgm;
create index books_title_gin_idx on books using gin (lower(title) gin_trgm_ops);