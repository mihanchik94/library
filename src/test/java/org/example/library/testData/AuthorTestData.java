package org.example.library.testData;

import org.example.library.model.Author;

public final class AuthorTestData {

    public static final Long AUTHOR_ID = 1L;
    public static final String AUTHOR_NAME_LEV_TOLSTOY = "Lev Tolstoy";

    private AuthorTestData() {
        throw new UnsupportedOperationException("Cannot create an instance");
    }

    public static Author createAuthorWithId1L() {
        return Author.builder()
                .withId(AUTHOR_ID)
                .withName(AUTHOR_NAME_LEV_TOLSTOY)
                .build();
    }
}
