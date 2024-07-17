package com.redhat.api;

import com.redhat.model.BusRoute;
import com.redhat.model.BusRouteRequest;
import io.quarkus.infinispan.client.Remote;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.infinispan.client.hotrod.RemoteCache;

import java.util.concurrent.CompletionStage;

@Path("/api/cache")
@Produces(MediaType.APPLICATION_JSON)
public class DemoDataGridResource {
    static final String CACHE_NAME = "demo-data-grid";
    @Inject
    @Remote(CACHE_NAME)
    RemoteCache<String, BusRoute> cache;

    @PUT
    @Path("/{key}")
    public CompletionStage<String> put(@PathParam("key") String key, BusRouteRequest routeRequest) {
        BusRoute route = new BusRoute(routeRequest.routeId(), routeRequest.routeName(), routeRequest.startLocation(), routeRequest.endLocation(), routeRequest.operatingHours(), routeRequest.busFrequency());
        return cache.putAsync(key, route).thenApply(g -> "Object Saved!").exceptionally(Throwable::getMessage);
    }

    @GET
    @Path("/{key}")
    public CompletionStage<BusRoute> get(@PathParam("key") String key) {
        return cache.getAsync(key);
    }

    @DELETE
    @Path("/{key}")
    public CompletionStage<BusRoute> delete(@PathParam("key") String key) {
        return cache.removeAsync(key);
    }
}
