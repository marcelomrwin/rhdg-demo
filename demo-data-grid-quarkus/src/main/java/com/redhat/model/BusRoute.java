package com.redhat.model;

import org.infinispan.protostream.annotations.ProtoFactory;
import org.infinispan.protostream.annotations.ProtoField;
import org.infinispan.protostream.annotations.ProtoTypeId;

@ProtoTypeId(1)
public class BusRoute {
    private final String routeId;
    private final String routeName;
    private final String startLocation;
    private final String endLocation;
    private final String operatingHours;
    private final Integer busFrequency;

    @ProtoFactory
    public BusRoute(String routeId, String routeName, String startLocation, String endLocation, String operatingHours, Integer busFrequency) {
        this.routeId = routeId;
        this.routeName = routeName;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.operatingHours = operatingHours;
        this.busFrequency = busFrequency;
    }

    @ProtoField(number = 1)
    public String getRouteId() {
        return routeId;
    }

    @ProtoField(number = 2)
    public String getRouteName() {
        return routeName;
    }

    @ProtoField(number = 3)
    public String getStartLocation() {
        return startLocation;
    }

    @ProtoField(number = 4)
    public String getEndLocation() {
        return endLocation;
    }

    @ProtoField(number = 5)
    public String getOperatingHours() {
        return operatingHours;
    }

    @ProtoField(number = 6)
    public Integer getBusFrequency() {
        return busFrequency;
    }
}
