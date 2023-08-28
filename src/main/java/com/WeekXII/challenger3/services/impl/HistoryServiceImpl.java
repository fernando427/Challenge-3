package com.WeekXII.challenger3.services.impl;

import com.WeekXII.challenger3.model.History;
import com.WeekXII.challenger3.model.Post;
import com.WeekXII.challenger3.repositories.HistoryRepository;
import com.WeekXII.challenger3.services.HistoryService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;

    public HistoryServiceImpl(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    @Override
    public void saveHistory(String status, Post post) {
        History history = new History();
        history.setPost(post);
        history.setDate(new Date());
        history.setStatus(status);
        historyRepository.save(history);
    }
}
