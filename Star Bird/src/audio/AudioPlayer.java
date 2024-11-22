package audio;

import javax.sound.sampled.*;

import untilz.LoadSave;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class AudioPlayer {
    private static AudioPlayer instance;
    private Map<String, Clip> musicClips;
    private Map<String, Float> musicVolumes;
    private boolean isMuted;


    private AudioPlayer() {
        musicClips = new HashMap<>();
        musicVolumes = new HashMap<>();
        initAudio();
    }

    private void initAudio() {
        loadMusic(SoundEffect.TAP.getName(), SoundEffect.TAP.getFilePath());
        loadMusic(SoundEffect.COIN_TAP.getName(), SoundEffect.COIN_TAP.getFilePath());
        loadMusic(SoundEffect.GOT_HIT.getName(), SoundEffect.GOT_HIT.getFilePath());
        loadMusic(SoundEffect.HAPPY_MOMENT.getName(), SoundEffect.HAPPY_MOMENT.getFilePath());
        loadMusic("game_loop", LoadSave.BGM);
    }

    public static AudioPlayer getInstance() {
        if (instance == null)
            instance = new AudioPlayer();
        return instance;
    }

    public void loadMusic(String name, String path) {
        try {
            File audioFile = new File(path);
            if (!audioFile.exists()) {
                System.out.println("Audio file not found at: " + path);
                return;
            }

            AudioInputStream audioInput = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInput);
        
            musicClips.put(name, clip);
            musicVolumes.put(name, 1.0f);
            
            System.out.println("Loaded audio: " + name);
        } catch (Exception e) { 
            System.out.println("Error loading audio: " + name);
            e.printStackTrace();
        }
    }

    public void play(String name, boolean loop) {
        Clip clip = musicClips.get(name);
        if (clip != null) {
            clip.setFramePosition(0);
            
            if (loop) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                clip.start();
            }
        }
    }

    public void stop(String name) {
        Clip clip = musicClips.get(name);
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.setFramePosition(0);
        }
    }

    public void stopAll() {
        for (Clip clip : musicClips.values()) {
            if (clip.isRunning()) {
                clip.stop();
                clip.setFramePosition(0);
            }
        }
    }

    public void setVolume(String name, float volume) {
        Clip clip = musicClips.get(name);
        if (clip != null) {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float dB = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
            gainControl.setValue(dB);
            musicVolumes.put(name, volume);
        }
    }

    public void mute() {
        isMuted = true;
        for (String name : musicClips.keySet()) {
            Clip clip = musicClips.get(name);
            if (clip != null) {
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(gainControl.getMinimum());
            }
        }
    }

    public void unmute() {
        isMuted = false;
        for (String name : musicClips.keySet()) {
            setVolume(name, musicVolumes.get(name));
        }
    }

    public void dispose() {
        stopAll();
        for (Clip clip : musicClips.values()) {
            clip.close();
        }
        musicClips.clear();
    }
}