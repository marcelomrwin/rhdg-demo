package com.redhat.config;

import com.redhat.model.BusRoute;
import com.redhat.model.BusRouteSchemaImpl;
import com.redhat.service.DemoDataGridCacheListener;
import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.commons.marshall.ProtoStreamMarshaller;
import org.infinispan.spring.starter.remote.InfinispanRemoteCacheCustomizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class DemoDataGridCacheConfig {

    static final String CACHE_NAME = "demo-data-grid";

    @Value("${app.cache.delete}")
    private Boolean cacheDelete;


    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public InfinispanRemoteCacheCustomizer caches() {
        return configurationBuilder -> {
            URI cacheConfigUri = cacheConfigURI("demoDataGridCache.xml");
            configurationBuilder.remoteCache(CACHE_NAME)
                    .configurationURI(cacheConfigUri);
            configurationBuilder.remoteCache(CACHE_NAME).marshaller(ProtoStreamMarshaller.class);
            configurationBuilder.addContextInitializer(new BusRouteSchemaImpl());
        };
    }

    private URI cacheConfigURI(String cacheConfigFile) {
        URI cacheConfigUri;
        try {
            cacheConfigUri = this.getClass().getClassLoader().getResource(cacheConfigFile).toURI();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return cacheConfigUri;
    }

    @Bean
    RemoteCache<String, BusRoute> remoteCache(RemoteCacheManager cacheManager) {
        RemoteCache<String, BusRoute> remoteCache = cacheManager.getCache(CACHE_NAME);
        remoteCache.addClientListener(new DemoDataGridCacheListener());
        return remoteCache;
    }

}
