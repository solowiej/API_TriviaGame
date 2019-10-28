package trivia;

import trivia.model.models_used_to_ask_users_questions.QuizCategory;
import trivia.model.models_used_to_ask_users_questions.QuizDifficulty;
import trivia.model.models_used_to_ask_users_questions.QuizParameters;
import trivia.model.models_used_to_ask_users_questions.QuizType;

import java.util.Arrays;
import java.util.Scanner;

public class ScannerContentLoader {
    private Scanner scanner = new Scanner(System.in);

    public void loadAmount(QuizParameters quizParameters) {
        do {
            try {
                System.out.println("Number of questions:");
                String line = scanner.nextLine();
                int questionsCount = Integer.parseInt(line);
                if (questionsCount < 1 || questionsCount > 50) {
                    System.err.println("The number of questions must be greater than 0 and also less than 50.");
                    continue;
                }
                quizParameters.setAmountOfQuestions(questionsCount);

            } catch (NumberFormatException nfe) {
                System.err.println("Wrong number.");
            }
        } while (quizParameters.getAmountOfQuestions() == null);
    }

    private QuizCategory checkIfIdIsDefinedInEnum(int categoryId) {
        return Arrays.stream(QuizCategory.values())
                .filter(quizCategory -> quizCategory.getId() == categoryId)
                .findFirst()
                .orElse(null);
    }

    public void loadCategory(QuizParameters quizParameters) {
        do {
            try {
                System.out.println("Category [enter the id]:");
                Arrays.asList(QuizCategory.values())
                        .forEach(quizCategory ->
                                System.out.println(quizCategory.getId() + " -> " + quizCategory.getName()));

                String line = scanner.nextLine();
                int categoryId = Integer.parseInt(line);
                quizParameters.setCategory(checkIfIdIsDefinedInEnum(categoryId));

            } catch (NumberFormatException nfe) {
                System.err.println("Wrong number.");
            }
        } while (quizParameters.getCategory() == null);
    }

    private QuizDifficulty checkIfProperDifficulty(String difficulty) throws IllegalArgumentException {
        return QuizDifficulty.valueOf(difficulty.toUpperCase());
    }

    public void loadDifficulty(QuizParameters quizParameters) {
        do {
            try {
                System.out.println("Difficulty:");
                Arrays.asList(QuizDifficulty.values()).forEach(System.out::println);

                String line = scanner.nextLine();

                quizParameters.setDifficulty(checkIfProperDifficulty(line));
            } catch (IllegalArgumentException iae) {
                System.err.println("Wrong parameter.");
            }
        } while (quizParameters.getDifficulty() == null);
    }

    public void loadType(QuizParameters quizParameters) {
        do {
            try {
                System.out.println("Type:");
                Arrays.asList(QuizType.values()).forEach(System.out::println);

                String line = scanner.nextLine();

                quizParameters.setQuizType(QuizType.valueOf(line.toUpperCase()));
            } catch (IllegalArgumentException iae) {
                System.err.println("Wrong parameter.");
            }
        } while (quizParameters.getQuizType() == null);
    }

    public String loadAnswer() {
        String answer;
        char sign;

        do {
            System.out.println("Your answer: ");

            answer = scanner.nextLine();
            sign = answer.toLowerCase().charAt(0);
        } while (sign < 'a' || sign > 'd');

        return answer;
    }
}
