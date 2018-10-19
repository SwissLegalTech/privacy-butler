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
    private final ArrayList<Pattern> SYNONYM_DATASTORAGECH;
    private final ArrayList<Pattern> SYNONYM_DATASTORAGEDE;
    private final ArrayList<Pattern> SYNONYM_DATASTORAGEUS;
    private final ArrayList<Pattern> SYNONYM_GEOTRACKING;
    private final ArrayList<Pattern> SYNONYM_WEITERGABEANDRITTE;
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
            add(Pattern.compile("(?i)Google Maps"));
            add(Pattern.compile("(?i)Maps"));
            add(Pattern.compile("(?i)Standort.*"));
            add(Pattern.compile("(?i)Ortung.*"));
            add(Pattern.compile("(?i)Positionsbestimmung.*"));
            add(Pattern.compile("(?i)Ortsbestimmung.*"));
            add(Pattern.compile("(?i)Aufenthaltsort.*"));
            add(Pattern.compile("(?i)Positionsdaten.*"));
            add(Pattern.compile("(?i)Geodaten.*"));
        }};

        SYNONYM_WEITERGABEANDRITTE = new ArrayList<Pattern>() {{
            add(Pattern.compile("(?i)Dritt.*"));
            add(Pattern.compile("(?i).*Partner.*"));
        }};

        SYNONYM_PROFILING = new ArrayList<Pattern>() {{
            add(Pattern.compile("(?i)Personendaten"));
            add(Pattern.compile("(?i).*Analytics"));
            add(Pattern.compile("(?i)Analytics"));
            add(Pattern.compile("(?i)Cookies"));
            add(Pattern.compile("(?i)personenbezogen.*"));
            add(Pattern.compile("(?i)personalisier.*"));
            add(Pattern.compile("(?i)kontaktinformationen"));
        }};
        
        SYNONYM_DATASTORAGECH = new ArrayList<Pattern>() {{
            add(Pattern.compile("(?i)schweizer Server"));
            add(Pattern.compile("(?i)ServerStandort Schweiz"));
            add(Pattern.compile("(?i)Server Standort Schweiz"));
        }};
        
        SYNONYM_DATASTORAGEDE = new ArrayList<Pattern>() {{
            add(Pattern.compile("(?i)deutschen Server"));
            add(Pattern.compile("(?i)ServerStandort Deutschland"));
            add(Pattern.compile("(?i)Server Standort Deutschland"));
        }};
        
        SYNONYM_DATASTORAGEUS = new ArrayList<Pattern>() {{
            add(Pattern.compile("Swiss-U.S. Privacy Shield"));
            add(Pattern.compile("Swiss-US-Privacy-Shield"));
            add(Pattern.compile("privacyshield.gov"));
            add(Pattern.compile("in den USA"));
        }};

        MAP = new HashMap<>();
        MAP.put("geotracking", SYNONYM_GEOTRACKING);
        MAP.put("dritte", SYNONYM_WEITERGABEANDRITTE);
        MAP.put("profiling", SYNONYM_PROFILING);
        MAP.put("datastoragech", SYNONYM_DATASTORAGECH);
        MAP.put("datastoragede", SYNONYM_DATASTORAGEDE);
        MAP.put("datastorageus", SYNONYM_DATASTORAGEUS);
    }

    public List<Pattern> getAllPatterns() {
        List<Pattern> matchedpatterns = new ArrayList<Pattern>();
        matchedpatterns.addAll(SYNONYM_GEOTRACKING);
        matchedpatterns.addAll(SYNONYM_WEITERGABEANDRITTE);
        matchedpatterns.addAll(SYNONYM_PROFILING);
        matchedpatterns.addAll(SYNONYM_DATASTORAGECH);
        matchedpatterns.addAll(SYNONYM_DATASTORAGEDE);
        matchedpatterns.addAll(SYNONYM_DATASTORAGEUS);
        return matchedpatterns;
    }

    public String getCategoryForToken(Token token) {
        Optional<Map.Entry<String, List<Pattern>>> matchinpatterncollection = MAP.entrySet().stream()
                .filter(stringListEntry -> stringListEntry.getValue().stream()
                        .anyMatch(pattern -> pattern.matcher(token.getText().getContent()).matches())).findFirst();

        if (!matchinpatterncollection.isPresent()) {
            throw new IllegalStateException("Ein ehemaliger Eintrag wurde nicht mehr gefunden");
        }
        return matchinpatterncollection.get().getKey();
    }
}
