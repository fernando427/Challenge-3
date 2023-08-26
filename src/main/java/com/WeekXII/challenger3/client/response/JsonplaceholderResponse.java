package com.WeekXII.challenger3.client.response;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class JsonplaceholderResponse {
    private Long userId;
    @Id
    private Long id;
    private String title;
    private String body;
}
