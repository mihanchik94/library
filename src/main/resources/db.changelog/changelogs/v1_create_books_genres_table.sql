create table books_genres(
    id bigserial primary key,
    book_id bigint references books(id),
    genre_id bigint references genres(id)
);