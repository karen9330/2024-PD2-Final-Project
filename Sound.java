import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.sound.sampled.*;

public class Sound {
    
    private HashMap<String, Clip> clips = new HashMap<>();
    public void loadBackgroundMusic(String key,String filePath) {
        Clip clip =  loadClip(filePath);
        if (clip != null) {
            clips.put(key, clip);
        }
    }
    public void loadSoundEffect(String key,String filePath) {
        Clip clip =  loadClip(filePath);
        if (clip != null) {
            clips.put(key, clip);
        }
    }
    
    private Clip loadClip(String filePath){

        try{
            File musicPath = new File(filePath);
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                return clip;
            } else {
                System.out.println("Can't find file");
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void playBackGround(String key) {
        Clip clip = clips.get(key);
        if (clip != null) {
            clip.setFramePosition(0);  
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void playBackGround(String key, float volume) {
        Clip clip = clips.get(key);
        if (clip != null) {
            volumeControl(volume, clip);
            clip.setFramePosition(0);  
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void play(String key,float volume){
        Clip clip = clips.get(key);
        if (clip != null) {
            volumeControl(volume, clip);
            clip.setFramePosition(0);  
            clip.start();
        }
    }

    public void play(String key){
        Clip clip = clips.get(key);
        if (clip != null) {
            clip.setFramePosition(0);  
            clip.start();
        }
    }

    public void stop(String key) {
        Clip clip = clips.get(key);
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    private void volumeControl(float volume, Clip clip){
        try{

            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(volume);

        }catch (IllegalArgumentException e) {
            System.err.println("Volume not supported: " + e.getMessage());
        }
        
    }

}
