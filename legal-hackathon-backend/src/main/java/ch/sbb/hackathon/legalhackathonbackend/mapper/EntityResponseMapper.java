package ch.sbb.hackathon.legalhackathonbackend.mapper;

import ch.sbb.hackathon.legalhackathonbackend.Data.Dataholder;
import ch.sbb.hackathon.legalhackathonbackend.model.ResponseDto;
import com.google.cloud.language.v1beta2.AnalyzeEntitiesResponse;
import com.google.cloud.language.v1beta2.AnalyzeSyntaxResponse;
import com.google.cloud.language.v1beta2.Entity;
import com.google.cloud.language.v1beta2.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class EntityResponseMapper {
    private static final Logger LOGGER = LoggerFactory.getLogger(EntityResponseMapper.class);

    public static ResponseDto map(AnalyzeEntitiesResponse analyzeEntitiesResponse, AnalyzeSyntaxResponse analyzeSyntaxResponse) {
        List<Entity> entitiesList = analyzeEntitiesResponse.getEntitiesList();

        List<String> orderedEntityNames = new ArrayList<>(entitiesList).stream().map(Entity::getName).sorted(String::compareTo).collect(Collectors.toList());

        List<String> wordstolookfor = new ArrayList<>(new LinkedHashSet<>(Dataholder.INSTANCE().getAllPatterns().stream()
                .map(pattern -> orderedEntityNames.stream()
                        .filter(s -> pattern.matcher(s).matches())
                        .collect(Collectors.toList()))
                .flatMap(List::stream)
                .collect(Collectors.toList())));


        ResponseDto.ResponseDtoBuilder builder = ResponseDto.builder();
        builder.foundEntities(wordstolookfor);
        builder.alowanceMap(classifyPermissions(wordstolookfor, analyzeSyntaxResponse));

        return builder.valid(true).build();
    }

    private static Map<String, Boolean> classifyPermissions(List<String> wordstolookfor, AnalyzeSyntaxResponse analyzeSyntaxResponse) {
        Map<String, Boolean> map = new HashMap<>();
        List<Token> tokens = analyzeSyntaxResponse.getTokensList().stream().filter(token -> wordstolookfor.contains(token.getText().getContent())).collect(Collectors.toList());

        //TODO: Magischer Filter um Negation auszugrenzen
        // Bug bei http://localhost:8080/demo?url=%27Ihre%20GPS-Daten%20werden%20von%20uns%20an%20s%20weitergeleitet%27


        for (Token t : tokens) {
            map.put(Dataholder.INSTANCE().getCategoryForToken(t), true);
        }
        return map;
    }
}
