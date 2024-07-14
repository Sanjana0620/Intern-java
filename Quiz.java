package placement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
class Question {
    private String questionText;
    private String[] options;
    private int correctAnswer;
    public Question(String questionText, String[] options, int correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }
    public String getQuestionText() {
        return questionText;
    }
    public String[] getOptions() {
        return options;
    }
    public int getCorrectAnswer() {
        return correctAnswer;
    }
    public boolean isCorrectAnswer(int answer) {
        return answer == correctAnswer;
    }
}
class Quiz {
    private List<Question> questions;
    private int score;

    public Quiz() {
        questions = new ArrayList<>();
        score = 0;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        for (Question question : questions) {
            System.out.println(question.getQuestionText());

            String[] options = question.getOptions();
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ". " + options[i]);
            }

            int userAnswer = getUserInput(scanner, options.length);
            if (question.isCorrectAnswer(userAnswer - 1)) {
                score++;
                System.out.println("Correct!\n");
            } else {
                System.out.println("Incorrect. The correct answer was " + (question.getCorrectAnswer() + 1) + ".\n");
            }
        }

        System.out.println("Your final score is: " + score + "/" + questions.size());
        scanner.close();
    }

    private int getUserInput(Scanner scanner, int optionsLength) {
        int userAnswer = -1;
        while (userAnswer < 1 || userAnswer > optionsLength) {
            System.out.print("Your answer (1-" + optionsLength + "): ");
            if (scanner.hasNextInt()) {
                userAnswer = scanner.nextInt();
                if (userAnswer < 1 || userAnswer > optionsLength) {
                    System.out.println("Invalid input. Please choose a number between 1 and " + optionsLength + ".");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // discard invalid input
            }
        }
        return userAnswer;
    }
}
public class OnlineQuizApplication {
    public static void main(String[] args) {
        Quiz quiz = new Quiz();
        quiz.addQuestion(new Question("What is the capital of France?", new String[]{"Berlin", "Madrid", "Paris", "Rome"}, 2));
        quiz.addQuestion(new Question("Which language is used for Android development?", new String[]{"Python", "Java", "C++", "Swift"}, 1));
        quiz.addQuestion(new Question("What is 5 + 3?", new String[]{"5", "7", "8", "10"}, 2));

        quiz.start();
    }
}
