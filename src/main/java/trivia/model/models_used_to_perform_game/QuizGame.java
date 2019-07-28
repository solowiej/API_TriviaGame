package trivia.model.models_used_to_perform_game;


import trivia.model.models_from_api.TriviaResponse;

import java.util.List;
import java.util.stream.Collectors;

public class QuizGame {
    private List<QuizQuestion> quizQuestions;
    private int currentQuestionNumber = 0;

    public QuizGame(TriviaResponse response) {
        quizQuestions = response.getResults()
                .stream()
                .map(triviaQuestion -> new QuizQuestion(triviaQuestion))
                .collect(Collectors.toList());
    }

    public boolean quizEnded() {
        return currentQuestionNumber >= quizQuestions.size();
    }

    public QuizQuestion getCurrentQuestion() {
        return quizQuestions.get(currentQuestionNumber);
    }

    public void submitAnswer(String answer) {
        int answerNumber = answer.toLowerCase().charAt(0) - 'a';

        if (quizQuestions.get(currentQuestionNumber).getAnswers().size() > answerNumber) {

            // pytanie na tore odpowiedzi udzielamy
            QuizQuestion question = quizQuestions.get(currentQuestionNumber);

            // odpowiedz urzytkownika
            QuizAnswer userAnswer = question.getAnswers().get(answerNumber);

            question.setSelectedAnswer(userAnswer);

            if (userAnswer.isCorrect()) {
                System.out.println("Good");
            } else {
                System.out.println("Bad, proper answer is: ");
                question.getAnswers()
                        .stream()
                        .filter(quizAnswer -> quizAnswer.isCorrect())
                        .findFirst()
                        .ifPresent(System.out::println);
            }
            currentQuestionNumber++;
        } else {
            System.err.println("Nie ma takiej odpowiedzi.");
        }
    }

    public void printScore() {
        long score = quizQuestions
                .stream()
                .filter(quizQuestion -> quizQuestion.getSelectedAnswer().isCorrect())
                .count();

        System.out.println("Your score: " + score);
    }
}
