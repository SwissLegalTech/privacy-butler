package ch.sbb.hackathon.legalhackathonbackend.service;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import com.google.cloud.language.v1beta2.AnalyzeEntitiesRequest;
import com.google.cloud.language.v1beta2.AnalyzeEntitiesResponse;
import com.google.cloud.language.v1beta2.Document;
import com.google.cloud.language.v1beta2.Document.Type;
import com.google.cloud.language.v1beta2.EncodingType;
import com.google.cloud.language.v1beta2.LanguageServiceClient;

@Service
public class DemoService {
    private static final Pattern URL_REGEX = Pattern.compile("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
    private static final Logger LOGGER = Logger.getLogger(DemoService.class.getSimpleName());

    public AnalyzeEntitiesResponse analyze(@NotNull String url, @NotNull Boolean geoTracking) {

        try (LanguageServiceClient language = LanguageServiceClient.create()) {
            // The text to analyze
            Document doc;
            if (URL_REGEX.matcher(url).find()) {
                doc = Document.newBuilder()
                        .setContent(getContent(url))
                        .setType(Type.HTML)
                        .build();
            } else {
                doc = Document.newBuilder()
                        .setContent(url)
                        .setType(Type.PLAIN_TEXT)
                        .build();
            }

            AnalyzeEntitiesRequest request = AnalyzeEntitiesRequest.newBuilder()
                    .setDocument(doc)
                    .setEncodingType(EncodingType.UTF16)
                    .build();

            /*
            Mehr info: https://cloud.google.com/natural-language/#nl_demo_section

            AnalyzeSyntaxRequest request1 = AnalyzeSyntaxRequest.newBuilder().setDocument(doc).setEncodingType(EncodingType.UTF16).build();
            language.analyzeSyntax(request1);

            */





            return language.analyzeEntities(request);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error during analyzing", e);
        }
        return AnalyzeEntitiesResponse.newBuilder().build();
    }

    private String getContent(@NotNull String url) {
        try (Scanner scanner = new Scanner(new URL(url).openConnection().getInputStream())) {
            scanner.useDelimiter("\\Z");
            return scanner.next();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error during content extraction", ex);
        }
        return "";
    }
}