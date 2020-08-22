package com.etcenteprise.newsoftheearth.repositories;

import com.etcenteprise.newsoftheearth.entities.NewsComments;

import java.util.List;

public interface NewsCommentsRepository {

    NewsComments saveComment(Long newsId, Long userId, String comment);

    boolean updateComment(Long newsId, Long userId, Long commentId);

    List<NewsComments> showAllCommentsByNewsId(long newsId);

    boolean deleteComment(Long newsId, Long userId, Long commentId);
}
