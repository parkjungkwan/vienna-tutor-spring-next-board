package com.bitcamp.api.article.model;

import org.springframework.stereotype.Component;
import lombok.*;
import lombok.extern.log4j.Log4j;
import java.time.LocalDateTime;


@Component
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {

    private Long id;
    private String title;
    private String content;
    private Long writerId;
    private Long boardId;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    
}
