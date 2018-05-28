package com.tony666.framework.transactions.core.repository.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventTypeAndData implements Serializable {

    private Long id;

    private String eventType;

    private String eventData;

    private String metadata;

    public EventTypeAndData(String eventType, String eventData, String metadata) {
        this.eventType = eventType;
        this.eventData = eventData;
        this.metadata = metadata;
    }
}
