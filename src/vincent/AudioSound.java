package vincent;

 
import java.applet.Applet;
import java.applet.AudioClip;

public class AudioSound {
	public static final AudioClip BALL = Applet.newAudioClip(AudioSound.class.getResource("ball2.wav"));
	public static final AudioClip GAMEOVER = Applet.newAudioClip(AudioSound.class.getResource("losingFailing.wav"));
	public static final AudioClip BACK = Applet.newAudioClip(AudioSound.class.getResource("Levelmusic.wav"));

}
