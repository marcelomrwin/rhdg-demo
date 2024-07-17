package com.redhat.model;

public record BusRouteRequest(String routeId,
                              String routeName,
                              String startLocation,
                              String endLocation,
                              String operatingHours,
                              Integer busFrequency) {
}
