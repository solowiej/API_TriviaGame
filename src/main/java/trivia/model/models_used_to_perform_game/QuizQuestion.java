package trivia.model.models_used_to_perform_game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import trivia.model.models_from_api.TriviaQuestion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizQuestion {
    private String question;
    private List<QuizAnswer> answers;
    private QuizAnswer selectedAnswer;


    public QuizQuestion(TriviaQuestion triviaQuestion) {
        this.question = triviaQuestion.getQuestion();
        this.selectedAnswer = null;
        this.answers = new ArrayList<>();

        this.answers.add(new QuizAnswer(triviaQuestion.getCorrect_answer(), true));
        this.answers.addAll(triviaQuestion
                .getIncorrect_answers()
                .stream()
                .map(incorrectAnswer -> new QuizAnswer(incorrectAnswer, false))
                .collect(Collectors.toList()));

        Collections.shuffle(this.answers);
    }


    @Override
    public String toString() {
        final char[] sign = new char[]{'a'};
        StringBuilder answerList = new StringBuilder();

        answers.stream().forEach(quizAnswer -> answerList.append((sign[0]++) + ") "
                + quizAnswer.getContent() + "\n"));

        return "Question: " + question + " \n\n\n" + answerList.toString();
    }
}
