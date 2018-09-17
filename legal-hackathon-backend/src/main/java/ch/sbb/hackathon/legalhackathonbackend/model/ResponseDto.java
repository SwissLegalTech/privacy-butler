package ch.sbb.hackathon.legalhackathonbackend.model;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Builder
@Getter
public class ResponseDto {
    public static final ResponseDto INVALID() {
        return builder().valid(false).build();
    }

    private boolean valid;
    @Builder.Default
    private Map<String, Boolean> alowanceMap = new HashMap<>();
    @Builder.Default
    private List<String> foundEntities = new ArrayList<>();

}
