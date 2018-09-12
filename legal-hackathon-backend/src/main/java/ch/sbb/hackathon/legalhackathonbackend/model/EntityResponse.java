package ch.sbb.hackathon.legalhackathonbackend.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EntityResponse {
    private String name;
    private Boolean geotrackingFulfilled;
}
