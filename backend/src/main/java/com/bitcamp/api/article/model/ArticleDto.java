package com.bitcamp.api.article.model;

import org.springframework.stereotype.Component;

import com.bitcamp.api.user.model.UserDto;

import lombok.*;
import lombok.extern.log4j.Log4j;
import java.time.LocalDateTime;


@Component
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {

    private Long articleId;
    private String title;
    private String content;
    private UserDto writer;
    private Long boardId;
    private Long writerId;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    
}
