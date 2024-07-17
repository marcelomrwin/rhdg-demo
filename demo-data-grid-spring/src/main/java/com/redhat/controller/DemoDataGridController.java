package com.redhat.controller;

import com.redhat.model.BusRoute;
import com.redhat.service.DemoDataGridService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/cache", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class DemoDataGridController {

    private final DemoDataGridService demoDataGridService;

    @Autowired
    public DemoDataGridController(DemoDataGridService demoDataGridService) {
        this.demoDataGridService = demoDataGridService;
    }

    @PutMapping("/{key}")
    public ResponseEntity<?> putOnCache(@PathVariable(name = "key") String key, @RequestBody BusRoute value) {
        demoDataGridService.putOnCache(key,value);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{key}")
    public ResponseEntity<?> getOnCache(@PathVariable(name = "key") String key) {
        return ResponseEntity.ok(demoDataGridService.getOnCache(key));
    }

    @DeleteMapping("/{key}")
    public ResponseEntity<?> deleteOnCache(@PathVariable(name = "key") String key) {
        return ResponseEntity.ok(demoDataGridService.deleteOnCache(key));
    }

}
