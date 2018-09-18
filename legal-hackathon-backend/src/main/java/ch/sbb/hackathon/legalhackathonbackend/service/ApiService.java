package ch.sbb.hackathon.legalhackathonbackend.service;

import ch.sbb.hackathon.legalhackathonbackend.mapper.EntityResponseMapper;
import ch.sbb.hackathon.legalhackathonbackend.model.ResponseDto;
import com.google.cloud.language.v1beta2.AnalyzeEntitiesRequest;
import com.google.cloud.language.v1beta2.AnalyzeEntitiesResponse;
import com.google.cloud.language.v1beta2.AnalyzeSyntaxRequest;
import com.google.cloud.language.v1beta2.AnalyzeSyntaxResponse;
import com.google.cloud.language.v1beta2.Document;
import com.google.cloud.language.v1beta2.Document.Type;
import com.google.cloud.language.v1beta2.EncodingType;
import com.google.cloud.language.v1beta2.LanguageServiceClient;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

@Service
public class ApiService {
    private static final Pattern URL_REGEX = Pattern.compile("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
    private static final Logger LOGGER = Logger.getLogger(ApiService.class.getSimpleName());

    public ResponseDto analyze(@NotNull String url) {

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

            AnalyzeEntitiesResponse analyzeEntitiesResponse = language
                    .analyzeEntities(AnalyzeEntitiesRequest.newBuilder()
                            .setDocument(doc)
                            .setEncodingType(EncodingType.UTF16)
                            .build()
                    );


            AnalyzeSyntaxResponse analyzeSyntaxResponse = language
                    .analyzeSyntax(AnalyzeSyntaxRequest.newBuilder()
                            .setDocument(doc)
                            .setEncodingType(EncodingType.UTF16)
                            .build()
                    );

            return EntityResponseMapper.map(analyzeEntitiesResponse, analyzeSyntaxResponse);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error during analyzing", e);
        }
        return ResponseDto.INVALID();
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