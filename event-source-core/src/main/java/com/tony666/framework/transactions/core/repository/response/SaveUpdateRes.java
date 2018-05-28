package com.tony666.framework.transactions.core.repository.response;

import com.tony666.framework.transactions.core.repository.request.EventTypeAndData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveUpdateRes implements Serializable {

    private Long entityId;

    private String entityType;

    private List<EventTypeAndData> events;

}
