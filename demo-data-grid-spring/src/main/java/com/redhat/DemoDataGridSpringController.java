package com.redhat;

import org.infinispan.client.hotrod.RemoteCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cache")
public class DemoDataGridSpringController {
    static final String CACHE_NAME = "demo-data-grid";
    private final RemoteCache<Object, Object> cache;

    @Autowired
    public DemoDataGridSpringController(RemoteCache<Object, Object> cache) {
        this.cache = cache;
    }

    @PutMapping
    public ResponseEntity<?> putOnCache(@RequestParam(name = "key") String key, @RequestParam(name = "value") String value) {
        cache.put(key, value);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<?> getOnCache(@RequestParam(name = "key") String key) {
        return ResponseEntity.ok(cache.get(key));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteOnCache(@RequestParam(name = "key") String key) {
        return ResponseEntity.ok(cache.remove(key));
    }

}
