package com.redhat.model;

import org.infinispan.protostream.annotations.Proto;

import java.io.Serializable;
import java.util.List;
@Proto
public record BusRoute(String routeId,
                       String routeName,
                       String startLocation,
                       String endLocation,
                       List<String> stops,
                       String operatingHours,
                       int busFrequency) implements Serializable {
}
