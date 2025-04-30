package org.example.library.repository;

import org.example.library.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    @Query("select g from Genre g where g.id in :ids")
    List<Genre> findAllByIds(@Param("ids") Set<Long> ids);
}
