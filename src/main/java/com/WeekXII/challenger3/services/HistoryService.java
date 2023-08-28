package com.WeekXII.challenger3.services;


import com.WeekXII.challenger3.model.Post;

public interface HistoryService {
    void saveHistory(String status, Post post);
}
