package FileManagement;

import java.io.*;

import javax.sound.sampled.*;

/**
 * Semplice classe con un solo metodo, che serve per riprodurre uno specificato file Audio
 */
public class AudioPlayer {
    File audioFile; //File audio da riprodurre

    /**
     * Metodo che riproduce il file audio.
     *
     * @param audioFile Nome del file audio da riprodurre
     */
    public void play(String audioFile) {
        try {
            this.audioFile = new File(audioFile);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.audioFile);

            AudioFormat format = audioInputStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);

            Clip audioClip =(Clip) AudioSystem.getLine(info);

            audioClip.open(audioInputStream);
            audioClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
