package com.bitcamp.api.article.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;
import com.bitcamp.api.article.model.Article;
import com.bitcamp.api.article.model.ArticleDto;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>{
    // JPQL Default 
    @Query("select a "
    +" from articles a where a.board.id = :boardId ")
    public List<Article> getArticlesByBoardId(@Param("boardId") Long boardId );


    // Native
    @Query(value = "select *  from articles a where a.board.id = :boardId ", nativeQuery = true)
    public List<Map<String, Object>> getQnaArticles(@Param("boardId") Long boardId);  


    // JPSQL Return Type DTO
    String articleDtoMapping = "new com.bitcamp.api.article.model.ArticleDto(" +
        "a.id, a.title, a.content, a.writer.id, a.board.id " +
        ", a.regDate, a.modDate)";
    @Query("SELECT " + articleDtoMapping + " FROM articles a WHERE a.board.id = :boardId")
    public List<ArticleDto> getArticleDTOsByBoardId(@Param("boardId") Long boardId);


    List<Article> findAllByOrderByArticleIdDesc();

    
} 