package com.tony666.framework.transactions.core;

import com.tony666.framework.transactions.core.repository.response.SaveUpdateRes;

import java.util.Optional;

public interface AggregateRepository<E extends ProcessCommandAggregate, C extends Command> {

    Optional<SaveUpdateRes> save(C cmd);

    Optional<SaveUpdateRes> save(C cmd, String metadata);

    Optional<SaveUpdateRes> update(C cmd);

//    void find(String entityId, String entityType);

}
