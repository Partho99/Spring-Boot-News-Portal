package com.etcenteprise.newsoftheearth.repositories;

public interface NewsVotingRepository {

    boolean upVoteCounting(Long userId, Long newsId);

    boolean downVoteCounting(Long userId, Long newsId);

    Long voteCounter();
}
