package com.tony666.framework.transactions.core;

import com.tony666.framework.transactions.core.repository.request.EventTypeAndData;
import com.tony666.framework.transactions.core.repository.response.SaveUpdateRes;

import java.util.List;
import java.util.Optional;

public interface Crud {

    Optional<SaveUpdateRes> save(List<EventTypeAndData> eventTypeAndData);

    Optional<SaveUpdateRes> update(List<EventTypeAndData> eventTypeAndData);

//    void find(String entityId, String entityType);
}
