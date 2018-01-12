package site.root3287.sudo2.test.audio;

import site.root3287.sudo2.audio.Audio;
import site.root3287.sudo2.audio.AudioBuffer;
import site.root3287.sudo2.audio.AudioListener;
import site.root3287.sudo2.audio.AudioSource;

public class AudioTest {
	public static void main(String[] args) {
		Audio.init();
		AudioListener.setListenerData();
		
		AudioBuffer b = new AudioBuffer("/sample.wav");
		
		AudioSource s = new AudioSource();
		s.play(b);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		s.dispose();
		b.dispose();
		Audio.destroy();
	}
}
