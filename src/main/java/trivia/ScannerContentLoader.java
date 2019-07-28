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
                System.out.println("Podaj liczbę pytań:");
                String line = scanner.nextLine();
                int questionsCount = Integer.parseInt(line);
                if (questionsCount < 1 || questionsCount > 50) {
                    System.err.println("Ilość pytań musi być większa od 0, oraz mniejsza niż 50.");
                    continue;
                }

                // ustawiono
                quizParameters.setAmountOfQuestions(questionsCount);
            } catch (NumberFormatException nfe) {
                System.err.println("Niepoprawna liczba.");
            }
        } while (quizParameters.getAmountOfQuestions() == null);
    }

    private QuizCategory sprawdzCzyIdZnajdujeSieWEnum(int categoryId) {
        return Arrays.stream(QuizCategory.values())
                .filter(quizCategory -> quizCategory.getId() == categoryId)
                .findFirst()
                .orElse(null);
    }

    public void loadCategory(QuizParameters quizParameters) {
        do {
            try {
                System.out.println("Podaj kategorię [wpisz identyfikator]:");
                Arrays.asList(QuizCategory.values())
                        .forEach(quizCategory ->
                                System.out.println(quizCategory.getId() + " -> " + quizCategory.getName()));

                String line = scanner.nextLine();
                int categoryId = Integer.parseInt(line);

                // ustawiono
                quizParameters.setCategory(sprawdzCzyIdZnajdujeSieWEnum(categoryId));
            } catch (NumberFormatException nfe) {
                System.err.println("Niepoprawna liczba.");
            }
        } while (quizParameters.getCategory() == null);
    }

    private QuizDifficulty sprawdzCzyPoprawneDifficulty(String difficulty) throws IllegalArgumentException {
        return QuizDifficulty.valueOf(difficulty.toUpperCase());
    }

    public void loadDifficulty(QuizParameters quizParameters) {
        do {
            try {
                System.out.println("Podaj poziom trudności [wpisz nazwę]:");
                Arrays.asList(QuizDifficulty.values()).forEach(System.out::println);

                String line = scanner.nextLine();

                quizParameters.setDifficulty(sprawdzCzyPoprawneDifficulty(line));
            } catch (IllegalArgumentException iae) {
                System.err.println("Niepoprawne wejście.");
            }
        } while (quizParameters.getDifficulty() == null);
    }

    public void loadType(QuizParameters quizParameters) {
        do {
            try {
                System.out.println("Podaj typ pytania [wpisz nazwę]:");
                Arrays.asList(QuizType.values()).forEach(System.out::println);

                String line = scanner.nextLine();

                quizParameters.setQuizType(QuizType.valueOf(line.toUpperCase()));
            } catch (IllegalArgumentException iae) {
                System.err.println("Niepoprawne wejście.");
            }
        } while (quizParameters.getQuizType() == null);
    }

    public String loadAnswer() {
        String answer;
        char sign;

        do {
            System.out.println("Podaj odpowiedz: ");

            answer = scanner.nextLine();
            sign = answer.toLowerCase().charAt(0);
        } while (sign < 'a' || sign > 'd');

        return answer;
    }
}
