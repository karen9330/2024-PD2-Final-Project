import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class GUI {
    public static void main(String args[]) {
        Recover();
    }
    public static void greeting() {
        System.out.println("========================================================================");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("pic/greeting.txt"),("UTF-8")) );
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            reader.close();
            System.out.println("\n\n> This is a Simple guess word game");
            System.out.println("> In each round please input a character!");
            System.out.println("> And you have 6 lives!");
            System.out.println("> Are you Readdy? Let's Go");

        } catch (IOException e) {
            e.printStackTrace();
        }
       
    }
    public static void show(String show){
        System.out.println("========================================================================");
        System.out.println("> The word to be guess is "+show);
        System.out.println("> Please input your answer (one character)");
    }
    public static void errorinput(){
        System.out.println("\n\n> Sorry! The format is not correct >__<");
        System.out.println("> Please input your answer (one character)");
    }
    public static void winmsg(){
        HangMan.player.stop("hangManBackGround");
        HangMan.player.play("hangManWin");
        System.out.println("========================================================================");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("pic/win.txt"),("UTF-8")) );
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            reader.close();
            System.out.println("\n\n> You win the game!");

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("========================================================================");
        try {
            Thread.sleep(1000);
            HangMan.player.stop("HangManWin");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static void recover(){
        HangMan.player.stop("hangManBackGround");
        System.out.println("========================================================================");
        System.out.println("> Do you want to Recover?");
        System.out.println("> Please input your answer (Y/N)");
    }
    public static void lose(){
        HangMan.player.stop("hangManBackGround");
        HangMan.player.play("hangmanGameOver");
        System.out.println("========================================================================");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("pic/lose.txt"),("UTF-8")) );
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            reader.close();
            System.out.println("\n\n> You lose the game!");
            System.out.printf("> The correct answer is \"%s\" !\n",HangMan.hiddenAnswer);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("========================================================================");
        try {
            Thread.sleep(1000);
            HangMan.player.stop("HangmanGameover");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static void wrong(){
        System.out.println("\n> Oops! You typed the wrong character!");
        System.out.println("> There is no character in this word match your input >__<");
        System.out.printf("> The lives remain %d !\n",HangMan.lives);
        System.out.println("========================================================================");
        try {
            String file = "pic/hangMan" + HangMan.lives +".txt";
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),("UTF-8")) );
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void correct(){
        System.out.println("\n> Congradulation! You typed the right character!");
        System.out.println("> Good Job!");
        System.out.printf("> The lives remain %d !\n",HangMan.lives);
    }
    public static void Recover(){
        System.out.println("========================================================================");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("pic/recover.txt"),("UTF-8")) );
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            reader.close();
            System.out.println("\n\n> Let's Play Rock-Paper-Sissor!");
            System.out.println("> Please input your choice(R/S/P)");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void bonusgreeting(){
        System.out.println("========================================================================");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("pic/bonus.txt"),("UTF-8")) );
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            reader.close();
            System.out.println("\n\n> This is a Classic guess game");
            System.out.println("> In each round please input a 4 different digits!");
            System.out.println("> And you have 10 lives!");
            System.out.println("> Are you Readdy? Let's Go");
            System.out.println("========================================================================");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void bonuslose(){
        HangMan.player.play("hangmanGameOver");
        System.out.println("========================================================================");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("pic/bonuslose.txt"),("UTF-8")) );
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            reader.close();
            System.out.println("\n\n> You lose the bonus game >__<");

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(1000);
            HangMan.player.stop("HangmanGameover");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static void bonuswin(){
        HangMan.player.play("hangManWin");
        System.out.println("========================================================================");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("pic/bonuswin.txt"),("UTF-8")) );
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            reader.close();
            System.out.println("\n\n> You win the bonus game!");
            Thread.sleep(1500);
            HangMan.player.stop("HangManWing");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
