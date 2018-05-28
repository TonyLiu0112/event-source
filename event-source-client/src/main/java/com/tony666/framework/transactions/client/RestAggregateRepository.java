package com.tony666.framework.transactions.client;

import com.alibaba.fastjson.JSON;
import com.tony666.framework.transactions.core.*;
import com.tony666.framework.transactions.core.repository.request.EventTypeAndData;
import com.tony666.framework.transactions.core.repository.response.SaveUpdateRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RestAggregateRepository<E extends ProcessCommandAggregate<C>, C extends Command> implements AggregateRepository<E, C> {

    private final Logger logger = LoggerFactory.getLogger(RestAggregateRepository.class);

    private final Class<ProcessCommandAggregate<C>> clazz;
    private final Crud crud;

    public RestAggregateRepository(Class<ProcessCommandAggregate<C>> clazz, Crud crud) {
        this.clazz = clazz;
        this.crud = crud;
    }

    @Override
    public Optional<SaveUpdateRes> save(C cmd) {
        return save(cmd, "");
    }

    @Override
    public Optional<SaveUpdateRes> save(C cmd, String metadata) {
        try {
            ProcessCommandAggregate aggregate = clazz.newInstance();
            //noinspection unchecked
            List<Event> events = aggregate.processCommand(cmd);
            List<EventTypeAndData> eventTypeAndDataList = events.stream().map(event -> new EventTypeAndData(event.getClass().getName(), JSON.toJSONString(event), metadata)).collect(Collectors.toList());
            return crud.save(eventTypeAndDataList);
        } catch (Exception e) {
            logger.error("save command error.", e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<SaveUpdateRes> update(C cmd) {
        try {
            ProcessCommandAggregate aggregate = clazz.newInstance();
            //noinspection unchecked
            List<Event> events = aggregate.processCommand(cmd);
            List<EventTypeAndData> eventTypeAndDataList = events.stream().map(event -> new EventTypeAndData(event.getClass().getName(), JSON.toJSONString(event), null)).collect(Collectors.toList());
            return crud.update(eventTypeAndDataList);
        } catch (Exception e) {
            logger.error("update command error.", e);
            return Optional.empty();
        }
    }

//    @Override
//    public void find(String entityId, String entityType) {
//        crud.find(entityId, entityType);
//        // TODO: 2018/5/24 response object.
//    }
}
