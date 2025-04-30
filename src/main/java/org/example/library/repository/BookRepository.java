package org.example.library.repository;

import org.example.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("select b from Book b where lower(b.title) like lower(concat('%', :title, '%'))")
    List<Book> findAllByTitle(@Param("title") String title);

    @Query("select b from Book b join b.author a where lower(a.name) like lower(concat('%', :authorName, '%'))")
    List<Book> findAllByAuthor(@Param("authorName") String authorName);

    @Query("select b from Book b join b.genres g where lower(g.name) like lower(concat('%', :genreName, '%'))")
    List<Book> findAllByGenre(@Param("genreName") String genreName);
}
