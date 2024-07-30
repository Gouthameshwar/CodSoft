import java.util.Random;
import java.util.Scanner;

public class GuessingGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int roundsWon = 0;
        int totalRounds = 0;
        int totalAttempts = 0;
        boolean playAgain;

        do {
            playAgain = false;
            int attemptsUsed = playRound(scanner);
            totalRounds++;
            totalAttempts += attemptsUsed;

            if (attemptsUsed > 0) {
                roundsWon++;
            }

            System.out.println("Do you want to play another round? (yes/no)");
            String response = scanner.next();
            if (response.equalsIgnoreCase("yes")) {
                playAgain = true;
            }
        } while (playAgain);

        System.out.println("Thanks for playing!");
        System.out.println("Total rounds played: " + totalRounds);
        System.out.println("Rounds won: " + roundsWon);
        System.out.println("Average attempts per round: " + (totalRounds > 0 ? (double) totalAttempts / totalRounds : 0));
        scanner.close();
    }

    private static int playRound(Scanner scanner) {
        Random random = new Random();
        int numberToGuess = random.nextInt(100) + 1;
        int attemptsLeft = 10;
        int attemptsUsed = 0;
        boolean correctGuess = false;

        System.out.println("Guess the number between 1 and 100. You have " + attemptsLeft + " attempts.");

        while (attemptsLeft > 0 && !correctGuess) {
            System.out.print("Enter your guess: ");
            int userGuess = scanner.nextInt();
            attemptsLeft--;
            attemptsUsed++;

            if (userGuess == numberToGuess) {
                System.out.println("Congratulations! You guessed the correct number.");
                correctGuess = true;
            } else if (userGuess > numberToGuess) {
                System.out.println("Your guess is too high. Attempts left: " + attemptsLeft);
            } else {
                System.out.println("Your guess is too low. Attempts left: " + attemptsLeft);
            }

            if (!correctGuess && Math.abs(userGuess - numberToGuess) <= 10) {
                System.out.println("Hint: You're within 10 of the correct number!");
            }
        }

        if (!correctGuess) {
            System.out.println("Sorry, you've run out of attempts. The correct number was " + numberToGuess + ".");
            return 0;
        }

        return attemptsUsed;
    }
}