package ch.sbb.hackathon.legalhackathonbackend.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.google.cloud.language.v1beta2.AnalyzeEntitiesResponse;
import com.google.cloud.language.v1beta2.Entity;

import ch.sbb.hackathon.legalhackathonbackend.model.EntityResponse;

public final class EntityResponseMapper {

    public static List<EntityResponse> map(AnalyzeEntitiesResponse analyzeEntitiesResponse) {
        return analyzeEntitiesResponse.getEntitiesList().stream()
                .map(EntityResponseMapper::mapEntity)
                .collect(Collectors.toList());
    }

    private static EntityResponse mapEntity(Entity entity) {
        return EntityResponse.builder()
                .name(entity.getName())
                .geotrackingFulfilled(entity.getName().toLowerCase().contains("geo"))
                .build();
    }

    private EntityResponseMapper() {
    }
}
