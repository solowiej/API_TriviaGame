package trivia;


import trivia.model.models_from_api.TriviaResponse;
import trivia.model.models_used_to_ask_users_questions.QuizParameters;
import trivia.model.models_used_to_perform_game.QuizGame;
import trivia.model.models_used_to_perform_game.QuizQuestion;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome in TRIVIA GAME! \n" );

        APITriviaURLBuilder builder = new APITriviaURLBuilder();
        QuizParameters quizParameters = new QuizParameters();
        ScannerContentLoader scannerContentLoader = new ScannerContentLoader();

        scannerContentLoader.loadAmount(quizParameters);
        scannerContentLoader.loadCategory(quizParameters);
        scannerContentLoader.loadDifficulty(quizParameters);
        scannerContentLoader.loadType(quizParameters);

        builder.loadParameters(quizParameters);

        String requestURL = builder.compileURL();
        TriviaAPI api = new TriviaAPI();

        TriviaResponse response = api.loadURLbyInputStream(requestURL);

        if (response.getResponse_code() == 0) {
            QuizGame game = new QuizGame(response);


            while (!game.quizEnded()) {
                QuizQuestion quizQuestion = game.getCurrentQuestion();
                System.out.println(quizQuestion);

                String answer = scannerContentLoader.loadAnswer();
                game.submitAnswer(answer);

            }
            game.printScore();
        }

    }
}
