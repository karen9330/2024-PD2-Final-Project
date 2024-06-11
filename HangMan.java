import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;


public class HangMan{

    private static final String CNNUrl = "https://edition.cnn.com/travel";
    public static int lives=6;
    static Scanner keyBoard = new Scanner(System.in);
    static Recover recover = new Recover();
    static Sound player = new Sound();
    static char[]userAnswers;
    static char[] hiddenText;
    static String hiddenAnswer;
    static boolean hasRecovered;
    static boolean isWin;
    static boolean tryagain;
    static boolean bonus;
    public static void getmusic(){
        player.loadSoundEffect("hangmanGameOver",Paths.get("music/hangManGameOver.wav").toString());
        player.loadSoundEffect("hangManRightAnswer", Paths.get("music/hangManRightAnswer.wav").toString());
        player.loadSoundEffect("hangManWrongAnswer", Paths.get("music/hangManWrongAnswer.wav").toString());
        player.loadSoundEffect("hangManWin",Paths.get("music/hangManWin.wav").toString());
        player.loadSoundEffect("hangManBackGround",Paths.get("music/hangManBackGroundMusic.wav").toString());
    }
    public static void getHiddentxt(){
        ArrayList<String> dictionary = HangMan.getNewsFromCNN();
        hiddenAnswer = dictionary.get((int)(Math.random() * dictionary.size()));
        hiddenText = hiddenAnswer.toCharArray();
        
    }
    public static void useranswerInitial(){
        userAnswers = new char[hiddenText.length];
        for(int i=0;i<hiddenText.length;i++){
            userAnswers[i] = '?';
        }
        hasRecovered=false;
        isWin=false;
        tryagain=false;
        lives=6;
    }
    public static String updateshow(){
        String show="";
        if(hiddenText.length<4){
            for(int i =0;i<userAnswers.length;i++){
                if(userAnswers[i] == '?'){
                    show+=("_");
                }
                else{
                    show+=(userAnswers[i]);
                }
            }
        }
        else{
            for(int i =0;i<userAnswers.length;i++){
                if((i&1)==0){
                    show+=hiddenText[i];
                    userAnswers[i]=hiddenText[i];
                }
                else{
                    if(userAnswers[i] == '?'){
                        show+=("_");
                    }
                    else{
                        show+=(userAnswers[i]);
                    }
                }
            }
        }
        return show;
    }
    public static void check(String input){
        boolean found = false;
        for(int i=0;i<hiddenText.length;i++){
            if(input.toLowerCase().charAt(0) == hiddenText[i]){
                
                player.play("hangManRightAnswer");
                userAnswers[i] = hiddenText[i];
                found = true;
                
                
            }
        }
        if(found){
            GUI.correct();
        }
        if(!found){   
            player.play("hangManWrongAnswer");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            lives--;
            GUI.wrong();
            
            
        }
    }
    public static boolean checkWin(){
        for(int i=0;i<HangMan.userAnswers.length;i++){
            if(HangMan.userAnswers[i]=='?') return false;
        }
        return true;
    }
    public static void logicOfGame() throws InterruptedException{
        getmusic();
        player.play("hangManBackGround");
        GUI.greeting();
        getHiddentxt();
        useranswerInitial();
        updateshow();
        while (HangMan.lives>0) {
            GUI.show(updateshow());
            String input = keyBoard.next();
            while (input.length()!= 1 || !input.matches("[a-zA-Z]")) {
                GUI.errorinput();
                input = keyBoard.next();
            }
            check(input);
            if(checkWin()==true){
                GUI.winmsg();
                handleBonus();
                return;
            }
            if(HangMan.lives==0){
                if(!hasRecovered){
                    GUI.recover();
                    boolean rst=Recover.getRecover();
                    if(rst==true){
                        if(Recover.playRockPaperScissors()!=1){
                            Thread.sleep(250);
                            GUI.lose();
                            handleBonus();
                            break;
                        }
                    }
                    else{
                        GUI.lose();
                        handleBonus();
                        break;
                    }
                }
            }
        }
        keyBoard.close();
    }

    public static void handleBonus() throws InterruptedException {
        System.out.println("> Do you want to play guess number (bonus game)? You should type 'Y' or 'N'");
        while (true) {
            char choice = keyBoard.next().charAt(0);
            if (choice == 'Y') {
                boolean isWin = Bonus.getNewChance();
                if(isWin){
                    GUI.bonuswin();  
                    return;
                }
                else{
                    GUI.bonuslose();
                    return;
                }
                
            } else if (choice == 'N') {
                System.out.println("> OK! Goodbye, Have a nice day!");
                return;
            } else {
                System.out.println("You typed something wrong, please type 'Y' or 'N'");
            }
        }
    }


    public static ArrayList<String> getNewsFromCNN() {
        ArrayList<String> words = new ArrayList<>();
        try {
            
            Document doc = connectWithRetry(CNNUrl, 3, 10 * 1000);
            Elements links = doc.select("[data-open-link]");
            String link = new String();
            for(Element element : links){
                link = "https://edition.cnn.com" + element.attr("data-open-link");
                break;  
            }
            
            Document docNews =connectWithRetry(link, 3, 10 * 1000);
            String selector = "p.paragraph.inline-placeholder.vossi-paragraph-primary-core-light[data-editable=text]";
            Elements textBlocks = docNews.select(selector);
            for (Element textBlock : textBlocks) {
                String texts = textBlock.text();
                
                texts = texts.replaceAll("\\b[A-Z][a-zA-Z]*\\b", "");
                texts = texts.replaceAll("[^a-zA-z]", " ");
                texts = texts.replaceAll("\\b\\w\\b", " ");
                texts = texts.replaceAll("\\s+", " ").trim();
                String[] textArr = texts.split(" ");
                for(int i=0;i<textArr.length;i++){
                    words.add(textArr[i]);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return words;
    }

    public static Document connectWithRetry(String url, int retries, int timeout) throws IOException {
        IOException lastException = null;
        for (int i = 0; i < retries; i++) {
            try {
                return Jsoup.connect(url).timeout(timeout).get();
            } catch (IOException e) {
                lastException = e;
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        throw lastException;
    }
}