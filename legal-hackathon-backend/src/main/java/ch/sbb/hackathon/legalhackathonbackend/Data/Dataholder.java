package ch.sbb.hackathon.legalhackathonbackend.Data;

import com.google.cloud.language.v1beta2.Token;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

public final class Dataholder {
    private static Dataholder _instance;
    private final ArrayList<Pattern> SYNONYM_PROFILING;
    private final ArrayList<Pattern> SYNONYM_GEOTRACKING;
    private final ArrayList<Pattern> SYNONY_WEITERGABEANDRITTE;
    private final Map<String, List<Pattern>> MAP;

    public static Dataholder INSTANCE() {
        if (_instance == null) {
            _instance = new Dataholder();
        }
        return _instance;
    }

    private Dataholder() {
        SYNONYM_GEOTRACKING = new ArrayList<Pattern>() {{
            add(Pattern.compile("(?i)GPS-Koordinaten"));
            add(Pattern.compile("(?i)GPS-Daten"));
            add(Pattern.compile("(?i).*Koordinaten.*"));
            add(Pattern.compile("(?i)Standort.*"));
            add(Pattern.compile("(?i)Ortung.*"));
            add(Pattern.compile("(?i)Positionsbestimmung.*"));
            add(Pattern.compile("(?i)Ortsbestimmung.*"));
            add(Pattern.compile("(?i)Aufenthaltsort.*"));
            add(Pattern.compile("(?i)Positionsdaten.*"));
            add(Pattern.compile("(?i)Geodaten.*"));
        }};

        SYNONY_WEITERGABEANDRITTE = new ArrayList<Pattern>() {{
            add(Pattern.compile("(?i)Dritt.*"));
            add(Pattern.compile("(?i).*Partner.*"));
        }};

        SYNONYM_PROFILING = new ArrayList<Pattern>() {{
            add(Pattern.compile("(?i)Personendaten"));
            add(Pattern.compile("(?i)Google Analytics"));
            add(Pattern.compile("(?i)personenbezogen.*"));
            add(Pattern.compile("(?i)kontaktinformationen"));
        }};

        MAP = new HashMap<>();
        MAP.put("geotracking", SYNONYM_GEOTRACKING);
        MAP.put("dritte", SYNONY_WEITERGABEANDRITTE);
        MAP.put("profiling", SYNONYM_PROFILING);
    }

    public List<Pattern> getAllPatterns() {
        List<Pattern> matchedpatterns = new ArrayList<Pattern>();
        matchedpatterns.addAll(SYNONYM_GEOTRACKING);
        matchedpatterns.addAll(SYNONY_WEITERGABEANDRITTE);
        matchedpatterns.addAll(SYNONYM_PROFILING);
        return matchedpatterns;
    }

    public String getCategoryForToken(Token token) {
        Optional<Map.Entry<String, List<Pattern>>> matchinpatterncollection = MAP.entrySet().stream().filter(stringListEntry -> stringListEntry.getValue().stream().filter(pattern -> pattern.matcher(token.getText().getContent()).matches()).findFirst().isPresent()).findFirst();
        if (!matchinpatterncollection.isPresent()) {
            throw new IllegalStateException("Ein ehemaliger Eintrag wurde nicht mehr gefunden");
        }
        return matchinpatterncollection.get().getKey();
    }
}
