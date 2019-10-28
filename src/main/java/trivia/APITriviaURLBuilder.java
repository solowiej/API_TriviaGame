package trivia;

import trivia.model.models_used_to_ask_users_questions.QuizCategory;
import trivia.model.models_used_to_ask_users_questions.QuizDifficulty;
import trivia.model.models_used_to_ask_users_questions.QuizParameters;
import trivia.model.models_used_to_ask_users_questions.QuizType;

public class APITriviaURLBuilder {
    private static final String BASE_URL = "https://opentdb.com/api.php?amount={amount}";

    private final StringBuilder builder;

    public APITriviaURLBuilder() {
        builder = new StringBuilder(BASE_URL);
    }

    private void appendNumberOfQuestions(int count) {
        if (builder.toString().contains("{amount}")) {
            int position = builder.indexOf("{amount}");
            builder.replace(position, position + 8, String.valueOf(count));
        }
    }

    private void appendCategory(QuizCategory quizCategory) {
        if (!builder.toString().contains("&cateogry=") && quizCategory.getId() != -1) {
            builder.append("&category=");
            builder.append(quizCategory.getId());
        } else if (builder.toString().contains("&cateogry=")) {
            System.err.println("The category has already been added.");
        }
    }

    private void appendDifficulty(QuizDifficulty quizDifficulty) {
        if (!builder.toString().contains("&difficulty=") && quizDifficulty != QuizDifficulty.ANY) {
            builder.append("&difficulty=");
            builder.append(quizDifficulty.toString().toLowerCase());
        } else if (builder.toString().contains("&difficulty=")) {
            System.err.println("The difficulty level has already been set.");
        }
    }

    private void appendType(QuizType quizType) {
        if (!builder.toString().contains("&type=") && quizType != QuizType.ANY) {
            builder.append("&type=");
            builder.append(quizType.toString().toLowerCase());
        } else if (builder.toString().contains("&type=")) {
            System.err.println("The type of question has already been determined.");
        }
    }

    public String compileURL() {
        return builder.toString();
    }

    public void loadParameters(QuizParameters parameters) {
        appendNumberOfQuestions(parameters.getAmountOfQuestions());
        appendCategory(parameters.getCategory());
        appendDifficulty(parameters.getDifficulty());
        appendType(parameters.getQuizType());
    }

    @Override
    public String toString() {
        return "APITriviaURLBuilder{" +
                "builder=" + builder +
                '}';
    }
}
