package com.redhat.config;

import com.redhat.model.BusRouteSchemaImpl;
import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.protostream.GeneratedSchema;
import org.infinispan.query.remote.client.ProtobufMetadataManagerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProtobufSchemaUploader {

    @Autowired
    public ProtobufSchemaUploader(RemoteCacheManager cacheManager) {
        RemoteCache<String, String> metadataCache =
                cacheManager.getCache(ProtobufMetadataManagerConstants.PROTOBUF_METADATA_CACHE_NAME);
        GeneratedSchema schema = new BusRouteSchemaImpl();
        metadataCache.put(schema.getProtoFileName(), schema.getProtoFile());
    }
}
