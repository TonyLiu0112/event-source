package com.tony666.framework.transactions.client;

import com.tony666.framework.transactions.client.integration.CdcIntegration;
import com.tony666.framework.transactions.core.Crud;
import com.tony666.framework.transactions.core.repository.request.EventTypeAndData;
import com.tony666.framework.transactions.core.repository.response.SaveUpdateRes;
import com.wrench.utils.restfulapi.response.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public class RestCrud implements Crud {

    private final CdcIntegration cdcIntegration;

    public RestCrud(CdcIntegration cdcIntegration) {
        this.cdcIntegration = cdcIntegration;
    }

    @Override
    public Optional<SaveUpdateRes> save(List<EventTypeAndData> eventTypeAndData) {
        ResponseEntity<RestResponse<SaveUpdateRes>> result = cdcIntegration.save(eventTypeAndData);
        if (result.getStatusCode() == HttpStatus.CREATED) {
            return Optional.of(result.getBody().getData());
        }
        return Optional.empty();
    }

    @Override
    public Optional<SaveUpdateRes> update(List<EventTypeAndData> eventTypeAndData) {
        ResponseEntity<RestResponse<SaveUpdateRes>> update = cdcIntegration.update(eventTypeAndData);
        if (update.getStatusCode() == HttpStatus.CREATED) {
            return Optional.of(update.getBody().getData());
        }
        return Optional.empty();
    }

//    @Override
//    public void find(String entityId, String entityType) {
//        ResponseEntity<RestResponse> result = cdcIntegration.find(entityId, entityType);
//        if (result.getStatusCode() == HttpStatus.OK) {
//            RestResponse body = result.getBody();
//            // TODO: 2018/5/24 undefined response object.
//        }
//    }
}
