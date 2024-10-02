package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuoteDTO {
    private Long id;

    private String content;

    private Long authorId;

    private AuthorDTO authorDTO;
}
