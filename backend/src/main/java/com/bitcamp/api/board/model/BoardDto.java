package com.bitcamp.api.board.model;
import lombok.*;
import org.springframework.stereotype.Component;

import com.bitcamp.api.article.model.Article;

import java.util.*;

@Component
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDto {

    private Long boardId;
    private String title;
    private String description;
    private String regDate;
    private String modDate;
    private List<Article> articles;
    
}
