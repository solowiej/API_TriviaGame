package trivia.model.models_used_to_perform_game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizAnswer {
    private String content;
    private boolean isCorrect;

    @Override
    public String toString() {
        return content;
    }
}
