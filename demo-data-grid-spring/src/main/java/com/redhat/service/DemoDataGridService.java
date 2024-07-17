package com.redhat.service;

import com.redhat.model.BusRoute;
import org.infinispan.client.hotrod.RemoteCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoDataGridService {
    private final RemoteCache<String, BusRoute> cache;

    @Autowired
    public DemoDataGridService(RemoteCache<String, BusRoute> cache) {
        this.cache = cache;
    }

    public void putOnCache(String key, BusRoute value) {
        cache.put(key, value);
    }

    public BusRoute getOnCache(String key) {
        return cache.get(key);
    }

    public BusRoute deleteOnCache(String key) {
        return cache.remove(key);
    }
}
