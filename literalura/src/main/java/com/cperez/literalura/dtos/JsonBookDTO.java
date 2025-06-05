package com.cperez.literalura.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record JsonBookDTO(
        @JsonAlias("title")
        String title,
        @JsonAlias("languages")
        List<String> language,
        @JsonAlias("download_count")
        Integer downloadCount,
        @JsonAlias("authors")
        List<JsonAuthorDTO> author
) {
}

