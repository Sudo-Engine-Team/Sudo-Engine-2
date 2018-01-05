package site.root3287.sudo2.test.audio;

import java.io.IOException;

import site.root3287.sudo2.audio.Audio;
import site.root3287.sudo2.audio.AudioBuffer;
import site.root3287.sudo2.audio.AudioListener;
import site.root3287.sudo2.audio.AudioSource;

public class AudioTest {
	public static void main(String[] args) {
		Audio.init();
		AudioListener.setListenerData();
		
		AudioBuffer b = new AudioBuffer("bounce.wav");
		
		AudioSource s = new AudioSource();
		char in = ' ';
		while(in != 'q'){
			s.play(b);
		
			try {
				in = (char) System.in.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		s.dispose();
		b.dispose();
		Audio.destroy();
	}
}
