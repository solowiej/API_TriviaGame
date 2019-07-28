package trivia;


import com.google.gson.Gson;
import org.apache.commons.lang3.StringEscapeUtils;
import trivia.model.models_from_api.TriviaResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class TriviaAPI {
    private final static Gson GSON = new Gson();

    public TriviaResponse loadURLbyContent(String requestURL) {
        StringBuilder builder = new StringBuilder();
        try {
            URL url = new URL(requestURL);

            // wczytujemy treść z URL
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            url.openStream()))) {

                reader.lines().forEach(builder::append);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        TriviaResponse triviaResponse = GSON.fromJson(builder.toString(), TriviaResponse.class);

        triviaResponse.getResults()
                .forEach(triviaQuestion ->
                        triviaQuestion.setQuestion(StringEscapeUtils.unescapeHtml4(triviaQuestion.getQuestion())));

        return triviaResponse;

    }

    public TriviaResponse loadURLbyInputStream(String requestURL) {
        TriviaResponse triviaResponse = null;
        try {
            URL url = new URL(requestURL);
            triviaResponse = GSON.fromJson(new InputStreamReader(url.openStream()), TriviaResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        triviaResponse.getResults()
                .forEach(triviaQuestion ->
                        triviaQuestion.setQuestion(StringEscapeUtils.unescapeHtml4(triviaQuestion.getQuestion())));

        return triviaResponse;
    }
}
