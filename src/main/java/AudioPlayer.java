import java.io.*;

import javax.sound.sampled.*;

public class AudioPlayer {
    File audioFile;
    public void play(String audioFile) {
        try {
            this.audioFile = new File(audioFile);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.audioFile);

            AudioFormat format = audioInputStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);

            Clip audioClip =(Clip) AudioSystem.getLine(info);

            audioClip.open(audioInputStream);
            audioClip.start();
            //audioClip.close();
            //audioInputStream.close();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
