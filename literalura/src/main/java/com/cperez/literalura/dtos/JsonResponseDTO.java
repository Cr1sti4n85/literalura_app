package com.cperez.literalura.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record JsonResponseDTO(
        @JsonAlias("results")
        List<JsonBookDTO> results
) {
}
