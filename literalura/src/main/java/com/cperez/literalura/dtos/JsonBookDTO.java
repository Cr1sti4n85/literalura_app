package com.cperez.literalura.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record JsonBookDTO(
        @JsonAlias("title")
        String title,
        @JsonAlias("languages")
        String language,
        @JsonAlias("download_count")
        Integer downloadCount,
        @JsonAlias("authors")
        String author
) {
}

