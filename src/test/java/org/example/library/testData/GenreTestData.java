package org.example.library.testData;

import org.example.library.model.Genre;

import java.util.Set;

public final class GenreTestData {
    public static final Set<Long> GENRE_IDS = Set.of(1L, 2L);
    public static final String GENRE_NAME_NOVEL = "Novel";
    public static final String GENRE_NAME_HISTORICAL = "Historical";

    private GenreTestData() {
        throw new UnsupportedOperationException("Cannot create an instance");
    }

    public static Genre createGenreWithId1L() {
        return createGenreBuilderOnlyWithId(1L)
                .withName(GENRE_NAME_NOVEL)
                .build();
    }

    public static Genre createGenreWithId2L() {
        return createGenreBuilderOnlyWithId(2L)
                .withName(GENRE_NAME_HISTORICAL)
                .build();
    }

    public static Set<Genre> createGenresSet() {
        return Set.of(createGenreWithId1L(), createGenreWithId2L());
    }

    private static Genre.GenreBuilder createGenreBuilderOnlyWithId(long id) {
        return Genre.builder().withId(id);
    }

}
