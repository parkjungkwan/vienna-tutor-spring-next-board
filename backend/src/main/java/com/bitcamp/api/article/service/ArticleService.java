package com.bitcamp.api.article.service;
import java.util.Optional;

import com.bitcamp.api.article.model.Article;
import com.bitcamp.api.article.model.ArticleDto;
import com.bitcamp.api.article.repository.ArticleRepository;
import com.bitcamp.api.board.model.Board;
import com.bitcamp.api.common.service.CommandService;
import com.bitcamp.api.common.service.QueryService;
import com.bitcamp.api.user.model.User;

public interface ArticleService extends CommandService<ArticleDto>, QueryService<ArticleDto>{

    default Article dtoToEntity(ArticleDto dto, User user, Board board) {
        return Article.builder()
            .articleId(dto.getArticleId())
            .board(board)
            .title(dto.getTitle())
            .content(dto.getContent())
            .writer(user)
            .build();
    }

    default ArticleDto entityToDto(Article optional){
        return ArticleDto.builder()
            .articleId(optional.getArticleId())
            .title(optional.getTitle())
            .content(optional.getContent())
            .build();
    }
    
}
