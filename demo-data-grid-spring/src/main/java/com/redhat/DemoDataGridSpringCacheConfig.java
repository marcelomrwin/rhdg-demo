package com.redhat;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.commons.dataconversion.MediaType;
import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.cache.StorageType;
import org.infinispan.eviction.EvictionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class DemoDataGridSpringCacheConfig {

    static final String CACHE_NAME = "demo-data-grid";
    private final RemoteCacheManager cacheManager;
    @Value("${app.cache.delete}")
    private Boolean cacheDelete;

    @Autowired
    public DemoDataGridSpringCacheConfig(RemoteCacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Bean
    RemoteCache<Object, Object> remoteCache() {
        RemoteCache<Object, Object> remoteCache = null;
        if (cacheDelete) {
            ConfigurationBuilder builder = new ConfigurationBuilder();
            builder.clustering()
                    .cacheMode(CacheMode.DIST_SYNC)
                    .l1()
                    .lifespan(1, TimeUnit.MINUTES)
                    .cleanupTaskFrequency(3, TimeUnit.MINUTES)
                    .encoding()
                    .key()
                    .mediaType(MediaType.APPLICATION_PROTOSTREAM)
                    .encoding()
                    .value()
                    .mediaType(MediaType.APPLICATION_PROTOSTREAM)
            ;

            builder.expiration().lifespan(5, TimeUnit.MINUTES).maxIdle(2, TimeUnit.MINUTES);
            builder.memory().storage(StorageType.OFF_HEAP).maxSize("256MB").whenFull(EvictionStrategy.REMOVE);
            cacheManager.administration().removeCache(CACHE_NAME);
            remoteCache = cacheManager.administration().createCache(CACHE_NAME, builder.build());

        } else {
            remoteCache = cacheManager.getCache(CACHE_NAME);
        }

        remoteCache.addClientListener(new DemoDataGridSpringCacheListener());
        return remoteCache;
    }
}
