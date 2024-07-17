package com.redhat;

import org.infinispan.client.hotrod.annotation.ClientListener;
import org.infinispan.client.hotrod.event.ClientCacheEntryCreatedEvent;
import org.infinispan.client.hotrod.event.ClientCacheEntryModifiedEvent;
import org.infinispan.client.hotrod.event.ClientCacheEntryRemovedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ClientListener
public class DemoDataGridSpringCacheListener {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @org.infinispan.client.hotrod.annotation.ClientCacheEntryCreated
    public void onClientCacheEntryCreated(ClientCacheEntryCreatedEvent<?> event) {
        logger.info("onClientCacheEntryCreated: {}", event);
    }

    @org.infinispan.client.hotrod.annotation.ClientCacheEntryModified
    public void onClientCacheEntryModified(ClientCacheEntryModifiedEvent<?> event) {
        logger.info("onClientCacheEntryModified: {}", event);
    }

    @org.infinispan.client.hotrod.annotation.ClientCacheEntryRemoved
    public void onClientCacheEntryRemoved(ClientCacheEntryRemovedEvent<?> event) {
        logger.info("onClientCacheEntryRemoved: {}", event);
    }

}
