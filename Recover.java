import java.util.Random;
import java.util.Scanner;

public class Recover {
	private static Scanner scanner = new Scanner(System.in);
    public static boolean getRecover() {
        while (true) {
            char choice = scanner.next().charAt(0);
            if (choice == 'Y') {
                return true;
            } else if (choice == 'N') {
                System.out.println("Ok, good luck!");
                return false;
            } else {
                System.out.println("You typed something wrong, please type 'Y' or 'N'");
            }
        }
    }

    public static int playRockPaperScissors() {
        while (true) {
            GUI.Recover();
            char user = scanner.next().charAt(0);
            if (user == 'R' || user == 'P' || user == 'S') {
                char computer=getComputerChoice();
                String computerChoice = outputComputerChoice(computer);
                System.out.println("> Computer choice: " + computerChoice);
                if (user == computer) {
                    System.out.println("> It's a draw! Sorry You can't Recover.");
                    return -1;
                } else if ((user == 'R' && computer == 'S') || (user == 'P' && computer == 'R') || (user == 'S' && computer == 'P')) {
                    HangMan.player.play("hangManBackGround");
                    System.out.println("> You win! You can Recover");
                    HangMan.lives++;
                    System.out.printf("> The lives remain %d !\n",HangMan.lives);
                    return 1;
                } else {
                    System.out.println("> Sorry you lose.");
                    return -2;
                }
            } else {
                System.out.println("> You typed something wrong, please try again.");
            }
        }        
    }

    private static char getComputerChoice() {
        char[] choices = {'R', 'P', 'S'};
        Random random = new Random();
        int randomIndex = random.nextInt(3);
        return choices[randomIndex];
    }
    
    private static String outputComputerChoice(char computerChoice) {
        switch (computerChoice) {
            case 'R':
                return "Rock";
            case 'P':
                return "Paper";
            case 'S':
                return "Scissors";
            default:
                return "Error";
        }
    }
}