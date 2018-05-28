package com.tony666.framework.transactions.client.integration;

import com.tony666.framework.transactions.core.repository.request.EventTypeAndData;
import com.tony666.framework.transactions.core.repository.response.SaveUpdateRes;
import com.wrench.utils.restfulapi.RestBuilder;
import com.wrench.utils.restfulapi.response.RestResponse;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * CDC client.
 *
 * @author Tony
 */
@FeignClient(name = "${spring.cloud.consul.cdc.serverName}", fallbackFactory = CdcIntegration.CdcIntegrationFallbackFactory.class)
public interface CdcIntegration {

    @PostMapping("save")
    ResponseEntity<RestResponse<SaveUpdateRes>> save(@RequestBody List<EventTypeAndData> eventTypeAndData);

    @PostMapping("update")
    ResponseEntity<RestResponse<SaveUpdateRes>> update(@RequestBody List<EventTypeAndData> eventTypeAndData);

    @GetMapping("find")
    ResponseEntity<RestResponse> find(@RequestParam("entityId") String entityId, @RequestParam("entityType") String entityType);

    @Component
    class CdcIntegrationFallbackFactory implements FallbackFactory<CdcIntegration> {

        private final Logger logger = LoggerFactory.getLogger(CdcIntegrationFallbackFactory.class);

        @Override
        public CdcIntegration create(final Throwable cause) {
            return new CdcIntegration() {
                @Override
                public ResponseEntity<RestResponse<SaveUpdateRes>> save(List<EventTypeAndData> eventTypeAndData) {
                    logger.error("cdc断路器: save接口不可用.");
                    return RestBuilder.serverError4Fallback();
                }

                @Override
                public ResponseEntity<RestResponse<SaveUpdateRes>> update(List<EventTypeAndData> eventTypeAndData) {
                    logger.error("cdc断路器: update接口不可用.");
                    return RestBuilder.serverError4Fallback();
                }

                @Override
                public ResponseEntity<RestResponse> find(String entityId, String entityType) {
                    logger.error("cdc断路器: find接口不可用.");
                    return RestBuilder.serverError4Fallback();
                }
            };
        }
    }
}
