create table borrows(
    id bigserial primary key,
    user_id bigint references users(id),
    book_copy_id bigint references book_copies(id),
    borrow_date timestamp not null,
    return_date timestamp
);