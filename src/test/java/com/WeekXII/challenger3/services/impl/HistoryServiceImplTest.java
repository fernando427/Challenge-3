package com.WeekXII.challenger3.services.impl;

import com.WeekXII.challenger3.model.History;
import com.WeekXII.challenger3.model.Post;
import com.WeekXII.challenger3.repositories.HistoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@SpringBootTest
class HistoryServiceImplTest {

    @InjectMocks
    private HistoryServiceImpl historyService;

    @Mock
    private HistoryRepository historyRepository;

    private Post post;

    @BeforeEach
    void setUp() {
        startHistory();
    }

    @Test
    void whenSaveHistory() {
        historyService.saveHistory("ENABLED", post);

        ArgumentCaptor<History> historyCaptor = ArgumentCaptor.forClass(History.class);
        verify(historyRepository).save(historyCaptor.capture());

        History capturedHistory = historyCaptor.getValue();

        assertNotNull(capturedHistory);
        assertEquals("ENABLED", capturedHistory.getStatus());
        assertNotNull(capturedHistory.getDate());
    }

    private void startHistory() {
        post = new Post(1L, 1L, "Title", "Content", "ENABLED", null, null);
    }
}