import java.security.SecureRandom;

public class MathBattle {

    static final int MIN_DICE_VALUE = 1;
    static final int MAX_DICE_VALUE = 10;
    static final int ERROR_TOLERANCE = 2;
    static final int GUESS_TOLERANCE = 4;

    enum GameResult { WIN, LOSS, DRAW };

    static final SecureRandom random = new SecureRandom();

    public static int mapDieToDifficulty ( int dieRoll ) {
        if ( dieRoll <= 3) return 1;
        else if ( dieRoll <= 7) return 2;
        else return 3;
        }

    public static int generateQuestion ( int difficultyLevel ) {
        if (difficultyLevel == 1) {
            int n1 = random.nextInt(10);
            int n2 = random.nextInt(10);
            char[] operators = {'+','-'};
            int randomIndex = random.nextInt(2);
            char operator = operators[randomIndex];

            if (operator == '+') {
                System.out.printf("%d + %d = ?\n",n1,n2);
                return n1 + n2;
            }
            else if (operator == '-') {
                System.out.printf("%d - %d = ?\n",n1,n2);
                return n1 - n2;
            }
        }
        else if (difficultyLevel == 2) {
            int n1 = random.nextInt(21);
            int n2 = random.nextInt(21);
            char[] operators = {'+','-','*'};
            int randomIndex = random.nextInt(3);
            char operator = operators[randomIndex];

            if (operator == '+') {
                System.out.printf("%d + %d = ?\n",n1,n2);
                return n1 + n2;
            }
            else if (operator == '-') {
                System.out.printf("%d - %d = ?\n",n1,n2);
                return n1 - n2;
            }
            else if (operator == '*') {
                System.out.printf("%d * %d = ?\n",n1,n2);
                return n1 * n2;
            }
        }
        else if (difficultyLevel == 3) {
            int n1 = random.nextInt(80) + 20;
            int n2 = random.nextInt(80) + 20;
            char[] operators = {'+','-','*','/','%'};
            int randomIndex = random.nextInt(5);
            char operator = operators[randomIndex];

            if (operator == '+') {
                System.out.printf("%d + %d = ?\n",n1,n2);
                return n1 + n2;
            }
            else if (operator == '-') {
                System.out.printf("%d - %d = ?\n",n1,n2);
                return n1 - n2;
            }
            else if (operator == '*') {
                System.out.printf("%d * %d = ?\n",n1,n2);
                return n1 * n2;
            }
            else if (operator == '/') {
                while (n2 == 0) {
                    n2 = random.nextInt(80) + 20;
                }
                System.out.printf("%d / %d = ?\n",n1,n2);
                return n1 / n2;
            }
            else if (operator == '%') {
                System.out.printf("%d %% %d = ?\n",n1,n2);
                return n1 % n2;
            }
        }
        return 0;
        }
    public static int simulateGuess(int correctAnswer) {
        boolean isPositive = random.nextBoolean();
        int amount = 0;

        if (isPositive) {
            amount = random.nextInt(GUESS_TOLERANCE + 1);
        } else {
            amount = -random.nextInt(GUESS_TOLERANCE + 1);
        }

        int result = correctAnswer + amount;
        return result;
    }

    public static int simulateGuess(int correctAnswer, boolean bias) {
        int currentTolerance = bias ? GUESS_TOLERANCE - 1 : GUESS_TOLERANCE;
        boolean isPositive = random.nextBoolean();
        int amount = 0;

        if (isPositive) {
            amount = random.nextInt(currentTolerance + 1);
        } else {
            amount = -random.nextInt(currentTolerance + 1);
        }

        int result = correctAnswer + amount;
        return result;
    }

    public static boolean checkAnswer(int expected, int given) {
        if (Math.abs(expected - given) <= ERROR_TOLERANCE) {
            return true;
        }
        return false;
    }

    public static void printRoundResult(String p1, String p2, int p1Guess, int p2Guess, boolean p1Correct, boolean p2Correct) {
        System.out.printf("%s guessed: %d\n", p1, p1Guess);
        if (p1Correct) System.out.println("+1 Point!"); 
        
        System.out.printf("%s guessed: %d\n", p2, p2Guess);
        if (p2Correct) System.out.println("+1 Point!");
    }

    public static void printSummary(String p1, String p2, int p1Score, int p2Score, GameResult result, String winnerName) {
        System.out.println("== Final Scores ==");

        System.out.printf("%s: %d\n",p1,p1Score);
        System.out.printf("%s: %d\n",p2,p2Score);

        if (result == GameResult.DRAW) {
            System.out.println("Result: It's a TIE!");
        } else {
            System.out.printf("Result: %s %sS!\n", winnerName, result);
        }
    }
    public static void main(String[] args) {

        if (args.length != 3) {
            System.err.println("You should give 3 parameters to play!");
            return;
        }

        int totalRounds = 0;

        try {
            totalRounds = Integer.parseInt(args[2]);

            if (totalRounds <= 0) {
                System.err.println("You should give a positive number to play!");
                return;
            }
            
        } catch (Exception e) {
            System.err.println("You should give a number as 3rd parameter!");
            return;
        }

        String p1 = args[0];
        String p2 = args[1];

        int p1Score = 0;
        int p2Score = 0;

        System.out.printf("MathBattle: %s vs. %s | Rounds: %d\n",p1,p2,totalRounds);
        for (int i = 0 ; i < totalRounds ; i++) {
            System.out.printf("\n[Round %d]\n", i + 1);
    
            int p1Roll = random.nextInt(MAX_DICE_VALUE) + MIN_DICE_VALUE;
            int p2Roll = random.nextInt(MAX_DICE_VALUE) + MIN_DICE_VALUE;

            System.out.printf("%s rolls %d\n", p1, p1Roll);
            System.out.printf("%s rolls %d\n", p2, p2Roll);

            System.out.printf("%s's question: ", p1);
            int p1CorrectAnswer = generateQuestion(mapDieToDifficulty(p1Roll));
            int p1GuessedAnswer = simulateGuess(p1CorrectAnswer);
            
            System.out.printf("%s's question: ", p2);
            int p2CorrectAnswer = generateQuestion(mapDieToDifficulty(p2Roll));
            int p2GuessedAnswer = simulateGuess(p2CorrectAnswer);

            boolean p1Correct = checkAnswer(p1CorrectAnswer, p1GuessedAnswer);
            boolean p2Correct = checkAnswer(p2CorrectAnswer, p2GuessedAnswer);

            if (p1Correct) p1Score++;
            if (p2Correct) p2Score++;

            printRoundResult(p1, p2, p1GuessedAnswer, p2GuessedAnswer, p1Correct, p2Correct);
            System.out.println();
        }

        GameResult finalResult;
        String winnerName = "";

        if (p1Score > p2Score) {
            finalResult = GameResult.WIN;
            winnerName = p1;
        } else if (p2Score > p1Score) {
            finalResult = GameResult.WIN;
            winnerName = p2;
        } else {
            finalResult = GameResult.DRAW;
        }

        printSummary(p1, p2, p1Score, p2Score, finalResult, winnerName);

    }
}
