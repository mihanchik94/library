create table book_copies(
    id bigserial primary key,
    inventory_number varchar(64) unique not null,
    book_id bigint references books(id),
    is_available boolean default true
);