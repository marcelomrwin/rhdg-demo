package com.redhat.config;

import com.redhat.model.BusRoute;
import io.quarkus.infinispan.client.Remote;
import io.quarkus.runtime.Startup;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.annotation.ClientCacheEntryCreated;
import org.infinispan.client.hotrod.annotation.ClientCacheEntryModified;
import org.infinispan.client.hotrod.annotation.ClientCacheEntryRemoved;
import org.infinispan.client.hotrod.annotation.ClientListener;
import org.infinispan.client.hotrod.event.ClientCacheEntryCreatedEvent;
import org.infinispan.client.hotrod.event.ClientCacheEntryModifiedEvent;
import org.infinispan.client.hotrod.event.ClientCacheEntryRemovedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
@Startup
public class CacheConfig {
    private static Logger logger = LoggerFactory.getLogger(CacheConfig.class);
    static final String CACHE_NAME = "demo-data-grid";

    @Inject
    @Remote(CACHE_NAME)
    RemoteCache<String, BusRoute> cache;

    void onStart(@Observes StartupEvent ev) {
        logger.info("Application started");
        cache.addClientListener(new CacheListener());
    }

    @ClientListener
    public static class CacheListener {

        @ClientCacheEntryCreated
        public void entryCreated(ClientCacheEntryCreatedEvent<String> event) {
            logger.info("Added entry: {}", event.getKey());
        }

        @ClientCacheEntryModified
        public void entryModified(ClientCacheEntryModifiedEvent<String> event) {
            logger.info("Modified entry: {}", event.getKey());
        }

        @ClientCacheEntryRemoved
        public void entryRemoved(ClientCacheEntryRemovedEvent<String> event) {
            logger.info("Removed entry: {}", event.getKey());
        }
    }
}
