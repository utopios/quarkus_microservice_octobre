package com.example.adapter.rest.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuoteDTO {

    private Long id;

    private String content;

    private Long authorId;

    private AuthorDTO authorDTO;
}
