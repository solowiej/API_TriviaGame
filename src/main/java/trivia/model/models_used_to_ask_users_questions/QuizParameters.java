package trivia.model.models_used_to_ask_users_questions;

import lombok.Data;

@Data
public class QuizParameters {
    private QuizCategory category;
    private QuizDifficulty difficulty;
    private QuizType quizType;
    private Integer amountOfQuestions;
}
